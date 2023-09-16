package wtf.l4j.api.auth;

import com.google.common.collect.Sets;
import lombok.experimental.UtilityClass;
import wtf.l4j.api.utils.render.CurryIdentifier;

import java.util.Set;

@UtilityClass
public class UserCapes {

    public final CurryIdentifier FRIEND_CAPE = new CurryIdentifier("textures/cape/cape1.png");
    public final CurryIdentifier NN_CAPE = new CurryIdentifier("textures/cape/cape2.png");
    public final CurryIdentifier WOW_CAPE = new CurryIdentifier("textures/cape/cape3.png");

    public final Set<String> FRIEND_UUIDS = Sets.newHashSet(
            "7f0e7596-ddf9-455b-acdb-7bce54a953b3",
            "242a3668-c8ab-4adf-948d-d2a45d672777",
            "a07b1e65-10d2-416d-9e20-86902fb3cb3f",
            "df6f9fa3-5ce0-48b2-a3a4-e010d521838e",
            "0660c7c2-834d-4105-8f06-19cf943b2240",
            "aa10a352-a7c0-4038-9a16-784fb5b6a88a",
            "773b9cd9-1176-4afe-b56d-fb1976257300",
            "1c6fe5c5-caea-4024-a4e6-ba2b4d23b77a",
            "83ac830f-1fa5-4e25-a2ef-e40834f15ef0"
    );

}
