package org.youngmonkeys.example.app.request;

import com.tvd12.ezyfox.binding.annotation.EzyObjectBinding;
import lombok.Data;

@Data
@EzyObjectBinding
public class MoveTransformResultRequest {
    private float x;
    private float y;
    private float z;

    @Override
    public String toString() {
        return "{" +
            "x=" + x +
            ", y=" + y +
            ", z=" + z +
            '}';
    }
}
