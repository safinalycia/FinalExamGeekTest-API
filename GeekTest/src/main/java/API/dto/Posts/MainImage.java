package API.dto.Posts;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

//Поле mainImage состоит из строк:
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "cdnUrl"
})
@Generated("jsonschema2pojo")
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor

public class MainImage {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("cdnUrl")
    public String cdnUrl;


}
