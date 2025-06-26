package uz.dev.edusphere.mapper;

import uz.dev.edusphere.entity.User;

import java.util.Objects;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 10:50
 **/

public class LongMapper {

    public static Long toLong(User user) {

        if (Objects.isNull(user)) {

            return null;

        }

        return user.getId();

    }

}
