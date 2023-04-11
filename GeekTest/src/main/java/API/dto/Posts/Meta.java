package API.dto.Posts;

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
        "prevPage",
        "nextPage",
        "count"
})
@Generated("jsonschema2pojo")
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor

//meta данные содержит в себе:

public class Meta {
    @JsonProperty("prevPage")
    public Integer prevPage;
    @JsonProperty("nextPage")
    public Integer nextPage;
    @JsonProperty("count")
    public Integer count;



}
