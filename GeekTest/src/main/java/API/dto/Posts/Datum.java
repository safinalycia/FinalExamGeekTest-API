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

//Ответ Json содержит(данные из ТЗ, либо через Postman создав запрос выдача постов):
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "description",
        "content",
        "authorId",
        "mainImage",
        "updatedAt",
        "createdAt",
        "labels",
        "delayPublishTo",
        "draft"
})
@Generated("jsonschema2pojo")
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor

//В ответе массив data содержит:

public class Datum {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("description")
    public String description;
    @JsonProperty("content")
    public String content;
    @JsonProperty("authorId")
    public Integer authorId;
    @JsonProperty("mainImage")
    public MainImage mainImage;
    @JsonProperty("updatedAt")
    public String updatedAt;
    @JsonProperty("createdAt")
    public String createdAt;
    @JsonProperty("labels")
    public List<Object> labels = new ArrayList<Object>();
    @JsonProperty("delayPublishTo")
    public String delayPublishTo;
    @JsonProperty("draft")
    public Boolean draft;


}
