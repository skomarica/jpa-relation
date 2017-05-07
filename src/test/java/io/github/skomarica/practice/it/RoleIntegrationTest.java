package io.github.skomarica.practice.it;

import io.github.skomarica.practice.dto.RoleCreateUpdateDto;
import io.github.skomarica.practice.dto.RolePermissionCreateUpdateDto;
import io.github.skomarica.practice.model.Role;
import io.github.skomarica.practice.model.RolePermission;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @author Sinisa Komarica
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(RoleIntegrationTest.class);

    private static final ParameterizedTypeReference<List<Role>> responseTypeRef =
            new ParameterizedTypeReference<List<Role>>() {
            };

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testA_findAll() {
        final ResponseEntity<List<Role>> responseEntity = restTemplate.exchange("/roles", HttpMethod.GET, null, responseTypeRef);
        assertThat(responseEntity.getBody()).hasSize(1);
    }

    @Test
    public void testB_create() {
        RoleCreateUpdateDto createDto = new RoleCreateUpdateDto(
                "USER",
                new HashSet<>(Arrays.asList(3L, 4L)),
                new HashSet<>(Arrays.asList(
                        new RolePermissionCreateUpdateDto(3L, false),
                        new RolePermissionCreateUpdateDto(4L, true)
                )));

        HttpEntity<RoleCreateUpdateDto> entity = new HttpEntity<>(createDto, new HttpHeaders());

        this.restTemplate.exchange("/roles", HttpMethod.POST, entity, Void.class);

        final ResponseEntity<Role> responseEntity = restTemplate.exchange("/roles/{id}", HttpMethod.GET, null, Role.class, 2);
        assertThat(responseEntity.getBody().getAttributeIds()).containsExactlyInAnyOrder(3L, 4L);
        assertThat(responseEntity.getBody().getRolePermissions()
                .stream().map(RolePermission::getPermissionId).collect(Collectors.toList())).containsExactlyInAnyOrder(3L, 4L);

        final ResponseEntity<List<Role>> allResponseEntity = restTemplate.exchange("/roles", HttpMethod.GET, null, responseTypeRef);
        assertThat(allResponseEntity.getBody()).hasSize(2);
    }

    @Test
    public void testC_update() {
        RoleCreateUpdateDto updateDto = new RoleCreateUpdateDto(
                "USER UPDATED",
                new HashSet<>(Arrays.asList(1L)),
                new HashSet<>(Arrays.asList(
                        new RolePermissionCreateUpdateDto(1L, false))));

        HttpEntity<RoleCreateUpdateDto> entity = new HttpEntity<>(updateDto, new HttpHeaders());

        this.restTemplate.exchange("/roles/{id}", HttpMethod.PUT, entity, Void.class, 2);

        //validate response
        final ResponseEntity<Role> responseEntity = restTemplate.exchange("/roles/{id}", HttpMethod.GET, null, Role.class, 2);
        assertThat(responseEntity.getBody().getAttributeIds()).containsExactlyInAnyOrder(1L);
        assertThat(responseEntity.getBody().getRolePermissions()
                .stream().map(RolePermission::getPermissionId).collect(Collectors.toList())).containsExactlyInAnyOrder(1L);

        final ResponseEntity<List<Role>> allResponseEntity = restTemplate.exchange("/roles", HttpMethod.GET, null, responseTypeRef);
        assertThat(allResponseEntity.getBody()).hasSize(2);
    }

    @Test
    public void testD_updateWithoutPermissions() {
        RoleCreateUpdateDto updateDto = new RoleCreateUpdateDto(
                "USER UPDATED", null, null);

        HttpEntity<RoleCreateUpdateDto> entity = new HttpEntity<>(updateDto, new HttpHeaders());

        this.restTemplate.exchange("/roles/{id}", HttpMethod.PUT, entity, Void.class, 2);

        //validate response
        final ResponseEntity<Role> responseEntity = restTemplate.exchange("/roles/{id}", HttpMethod.GET, null, Role.class, 2);
        assertThat(responseEntity.getBody().getAttributeIds()).isEmpty();
        assertThat(responseEntity.getBody().getRolePermissions()).isEmpty();

        final ResponseEntity<List<Role>> allResponseEntity = restTemplate.exchange("/roles", HttpMethod.GET, null, responseTypeRef);
        assertThat(allResponseEntity.getBody()).hasSize(2);
    }

    @Test
    public void testE_delete() {
        this.restTemplate.exchange("/roles/{id}", HttpMethod.DELETE, null, Void.class, 2);

        //validate response
        final ResponseEntity<List<Role>> allResponseEntity = restTemplate.exchange("/roles", HttpMethod.GET, null, responseTypeRef);
        assertThat(allResponseEntity.getBody()).hasSize(1);
        assertThat(allResponseEntity.getBody().get(0).getName()).isEqualTo("ADMIN");
    }
}
