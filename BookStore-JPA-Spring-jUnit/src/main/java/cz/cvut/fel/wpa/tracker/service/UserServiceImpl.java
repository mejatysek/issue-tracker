/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.service;

/**
 *
 * @author mickapa1
 */
//@Component
//public class UserServiceImpl extends AbstractDataAccessService implements UserService{

//    @Override
//    public List<UserDto> getAllUsers() {
//        List<User> users = genericDao.getAll(User.class);
//        List<UserDto> userDtos = new ArrayList<UserDto>();
//
//        for (User u : users) {
//            userDtos.add(new UserDto(u.getId(), u.getUserName(), u.getAge(), DtoTransformerHelper.getIdentifiers(u.getIssues())));
//        }
//        return userDtos;
//    }

//    @Override
//    public Long addUser(String userName, String password, int age) {
//        User newUser = new User();
//        newUser.setAge(age);
//        newUser.setPassword(password);
//        newUser.setUserName(userName);
//
//        return genericDao.saveOrUpdate(newUser).getId();
//    }

//    @Override
//    public void deleteUser(Long userId) {
//        genericDao.removeById(userId, User.class);
//    }

//    @Override
//    public UserDto getUserById(Long id) {
//        User u = genericDao.getByPropertyUnique("id", id, User.class);
//        return new UserDto(u.getId(), u.getUserName(), u.getAge(), DtoTransformerHelper.getIdentifiers(u.getIssues()));
//    }
//}
