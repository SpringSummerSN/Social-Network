package spring.summer.socialnetwork.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import spring.summer.socialnetwork.config.SecurityConfig;


import static org.hamcrest.CoreMatchers.is;


//@SpringBootTest
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
class UserControllerTest {
//
//    @Autowired
//    public UserRepository userRepository;
//
//    @Autowired
//    public PasswordEncoder passwordEncoder;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void add() throws Exception {
//
//        UserDTO user = UserDTO.builder()
//                .name("Tomasz")
//                .surname("Korniszuk")
//                .email("tomek@o2.pl")
//                .password("password")
//                .build();
//
//        ResultActions response = mockMvc.perform((post("/api/v1/user")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(user))));
//                response.andDo(print())
//                .andExpect(status().isCreated());
//
//
//
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void getUserByID() {
//
//
//
//    }
//
//    @Test
//    void userList() throws Exception {
//        ResultActions response = mockMvc.perform((get("/api/v1/user")
//                .contentType(MediaType.APPLICATION_JSON)));
//        response.andDo(print())
//                .andExpect(status().isCreated());
//    }

    @Test
    void update() {
    }
}