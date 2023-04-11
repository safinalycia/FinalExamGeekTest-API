package API.dto.Posts;

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


//в постах массив data, meta
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data",
        "meta"
})
@Generated("jsonschema2pojo")
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor

public class Posts {
    @JsonProperty("data")
    public List<Datum> data = new ArrayList<Datum>();
    @JsonProperty("meta")
    public Meta meta;



}
