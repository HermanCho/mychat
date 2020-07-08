package cn.edu.scau.dbclub.mychat.dao;

import cn.edu.scau.dbclub.mychat.pojo.do0.UserCollection;
import java.util.List;

public interface UserCollectionMapper {
    int deleteUserCollection(Integer id);

    int saveUserCollection(UserCollection record);

    UserCollection getUserCollection(Integer id);

    List<UserCollection> listUserCollections();

    int updateUserCollection(UserCollection record);
}