package org.d11.rest.integration;

import static org.d11.rest.model.D11RestMock.administratorToken;
import static org.d11.rest.model.D11RestMock.userToken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.model.jpa.User;
import org.d11.rest.repository.UserRepository;
import org.d11.rest.security.Role;
import org.d11.rest.util.D11RestProfile;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Tag(Tags.INTEGRATION_TEST)
@ActiveProfiles(profiles = D11RestProfile.TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class IntegrationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    private List<JpaRepository<?, ?>> jpaRepositories = new ArrayList<JpaRepository<?, ?>>();

    protected final static String INVALID_TOKEN = "INVALID_TOKEN";

    public IntegrationTests() {
    }

    public IntegrationTests(JpaRepository<?, ?>... jpaRepositories) {
        this.jpaRepositories.addAll(Arrays.asList(jpaRepositories));
    }

    protected MvcResult assertForbidden(MockHttpServletRequestBuilder mockHttpServletRequestBuilder) throws Exception {
        return assertForbidden(mockHttpServletRequestBuilder, INVALID_TOKEN, null);
    }

    protected MvcResult assertForbidden(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, String token) throws Exception {
        return assertForbidden(mockHttpServletRequestBuilder, token, null);
    }

    protected MvcResult assertForbidden(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, String token, Object content) throws Exception {
        MvcResult mvcResult = perform(mockHttpServletRequestBuilder, token, content, status().isForbidden());

        assertEquals("", mvcResult.getResponse().getContentAsString());

        return mvcResult;
    }

    protected MvcResult assertOk(MockHttpServletRequestBuilder mockHttpServletRequestBuilder) throws Exception {
        return assertOk(mockHttpServletRequestBuilder, INVALID_TOKEN, null);
    }

    protected MvcResult assertOk(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, String token) throws Exception {
        return assertOk(mockHttpServletRequestBuilder, token, null);
    }

    protected MvcResult assertOk(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, String token, Object content) throws Exception {
        MvcResult mvcResult = perform(mockHttpServletRequestBuilder, token, content, status().isOk());
        return mvcResult;
    }

    protected MvcResult assertNotFound(MockHttpServletRequestBuilder mockHttpServletRequestBuilder) throws Exception {
        return assertNotFound(mockHttpServletRequestBuilder, INVALID_TOKEN, null);
    }

    protected MvcResult assertNotFound(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, String token) throws Exception {
        return assertNotFound(mockHttpServletRequestBuilder, token, null);
    }

    protected MvcResult assertNotFound(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, String token, Object content) throws Exception {
        MvcResult mvcResult = perform(mockHttpServletRequestBuilder, token, content, status().isNotFound());
        return mvcResult;
    }

    protected MvcResult perform(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, String token, Object content, ResultMatcher resultMatcher) throws Exception {
        String contentString = (content != null ? this.objectMapper.writeValueAsString(content) : "");
        MvcResult mvcResult = this.mockMvc.perform(mockHttpServletRequestBuilder.contentType("application/json").header("Authorization", "Bearer " + token).content(contentString)).andExpect(resultMatcher).andReturn();

        return mvcResult;
    }

    protected <T extends Object> T readValue(MvcResult mvcResult, Class<T> clazz) throws Exception {
        T value = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(), clazz);
        return value;
    }

    protected <T extends Object> T readValue(MvcResult mvcResult, TypeReference<T> typeReference) throws Exception {
        T value = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(), typeReference);
        return value;
    }

    protected String token(User user) {
        user = this.userRepository.save(user);
        if(user.hasRole(Role.ADMINISTRATOR)) {
            return administratorToken(user);
        } else {
            return userToken(user);
        }
    }

    public List<JpaRepository<?, ?>> getJpaRepositories() {
        return jpaRepositories;
    }

    public void setJpaRepositories(List<JpaRepository<?, ?>> jpaRepositories) {
        this.jpaRepositories = jpaRepositories;
    }

    @SuppressWarnings("unchecked")
    protected <T extends JpaRepository<?, ?>> T getRepository(Class<T> clazz) {
        for(JpaRepository<?, ?> jpaRepository : this.jpaRepositories) {
            if(clazz.isAssignableFrom(jpaRepository.getClass())) {
                return (T) jpaRepository;
            }
        }
        return null;
    }

}
