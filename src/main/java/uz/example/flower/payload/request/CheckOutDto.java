package uz.example.flower.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckOutDto {
    @JsonProperty("flower_id")
    private Long flowerId;
    private Integer count;
}
