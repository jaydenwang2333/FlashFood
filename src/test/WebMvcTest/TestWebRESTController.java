package WebMvcTest;

import main.controller.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(AddressBookController.class)
@WebMvcTest(CategoryController.class)
@WebMvcTest(DishController.class)
@WebMvcTest(EmployeeController.class)
@WebMvcTest(OrdersController.class)
@WebMvcTest(SetmealController.class)
@WebMvcTest(ShoppingCartController.class)
@WebMvcTest(UserController.class)
public class TestWebRESTController {
    @WebMvcTest(CategoryController.class)
    public class DashboardControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private DashboardService dashboardService;

        @Test
        public void shouldReturnViewWithPrefilledData() throws Exception {
            when(dashboardService.getAnalyticsGraphData()).thenReturn(new Integer[]{13, 42});

            this.mockMvc
                    .perform(get("/dashboard"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("dashboard"))
                    .andExpect(model().attribute("user", "Duke"))
                    .andExpect(model().attribute("analyticsGraph", Matchers.arrayContaining(13, 42)))
                    .andExpect(model().attributeExists("quickNote"));
        }
    }

    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests(authorize -> authorize
                            .mvcMatchers(HttpMethod.GET, "/dashboard").permitAll()
                            .mvcMatchers(HttpMethod.GET, "/api/tasks/**").authenticated()
                            .mvcMatchers("/api/users/**").permitAll()
                            .mvcMatchers("/**").authenticated()
                    )
                    .httpBasic();
        }
    }

    @RestController
    @RequestMapping("/api/tasks")
    public class TaskController {

        private final TaskService taskService;

        public TaskController(TaskService taskService) {
            this.taskService = taskService;
        }

        @PostMapping
        public ResponseEntity<Void> createNewTask(@RequestBody JsonNode payload,
                                                  UriComponentsBuilder uriComponentsBuilder) {

            Long taskId = this.taskService.createTask(payload.get("taskTitle").asText());

            return ResponseEntity
                    .created(uriComponentsBuilder.path("/api/tasks/{taskId}").build(taskId))
                    .build();
        }

        @DeleteMapping
        @RolesAllowed("ADMIN")
        @RequestMapping("/{taskId}")
        public void deleteTask(@PathVariable Long taskId) {
            this.taskService.deleteTask(taskId);
        }
    }

    @WebMvcTest(TaskController.class)
// @Import(SecurityConfig.class) required when using a SecurityFilter chain bean -> Spring Boot 3.0
    class TaskControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private TaskService taskService;

        @Test
        public void shouldRejectCreatingReviewsWhenUserIsAnonymous() throws Exception {
            this.mockMvc
                    .perform(
                            post("/api/tasks")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("{\"taskTitle\": \"Learn MockMvc\"}")
                                    .with(csrf())
                    )
                    .andExpect(status().isUnauthorized());
        }

    }
    @Test
    public void shouldAllowDeletingReviewsWhenUserIsAdmin() throws Exception {
        this.mockMvc
                .perform(
                        delete("/api/tasks/42")
                                .with(user("duke").roles("ADMIN", "SUPER_USER"))
                                .with(csrf())
                )
                .andExpect(status().isOk());

        verify(taskService).deleteTask(42L);
    }


    @Test
    @WithMockUser("duke")
    public void shouldRejectDeletingReviewsWhenUserLacksAdminRole() throws Exception {
        this.mockMvc
                .perform(delete("/api/tasks/42"))
                .andExpect(status().isForbidden());
    }


}



