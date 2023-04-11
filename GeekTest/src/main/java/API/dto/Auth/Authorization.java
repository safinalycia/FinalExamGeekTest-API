package API.dto.Auth;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "username",
        "token",
        "roles"
})
@Generated("jsonschema2pojo")
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor

public class Authorization {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("username")
    public String username;
    @JsonProperty("token")
    public String token;
    @JsonProperty("roles")
    public List<String> roles = new ArrayList<String>();



}
