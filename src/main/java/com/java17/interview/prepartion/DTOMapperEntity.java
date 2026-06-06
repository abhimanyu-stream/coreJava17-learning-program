package com.java17.interview.prepartion;

public class DTOMapperEntity {
}
/***
 * In a Java Spring Boot application, converting DTOs (Data Transfer Objects) to Entity classes is a common task when dealing with service and controller layers. Here are several approaches to achieve this in a clean, manageable way:
 *
 * 1. Manual Mapping
 * This approach involves writing explicit methods to convert DTOs to Entities and vice versa.
 * This can be done in the DTO or Entity class itself, or in a separate Mapper class.

 * public class UserMapper {
 *     public static User toEntity(UserDTO userDTO) {
 *         User user = new User();
 *         user.setId(userDTO.getId());
 *         user.setName(userDTO.getName());
 *         user.setEmail(userDTO.getEmail());
 *         return user;
 *     }
 *
 *     public static UserDTO toDto(User user) {
 *         UserDTO userDTO = new UserDTO();
 *         userDTO.setId(user.getId());
 *         userDTO.setName(user.getName());
 *         userDTO.setEmail(user.getEmail());
 *         return userDTO;
 *     }
 * }
 * Pros: Full control over the conversion logic, which can be helpful for complex mappings.
 * Cons: Verbose and repetitive, especially for large applications.
 * 2. Using ModelMapper Library
 * ModelMapper is a popular library that can automatically map properties between objects with the same field names.

 * @Service
 * public class UserMapper {
 *     private final ModelMapper modelMapper = new ModelMapper();
 *
 *     public UserDTO toDto(User user) {
 *         return modelMapper.map(user, UserDTO.class);
 *     }
 *
 *     public User toEntity(UserDTO userDTO) {
 *         return modelMapper.map(userDTO, User.class);
 *     }
 * }
 * Pros: Quick and easy to set up, ideal for applications where mappings are straightforward.
 * Cons: Requires dependency (ModelMapper library), and may need configuration for more complex mappings.
 * 3. Using MapStruct Library
 * MapStruct is a code-generation library for Java that generates mappers at compile time, making it type-safe and efficient.
 * It uses annotations to define mappings between DTOs and Entities.

 * @Mapper(componentModel = "spring")
 * public interface UserMapper {
 *     UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
 *
 *     UserDTO toDto(User user);
 *     User toEntity(UserDTO userDTO);
 * }
 * Pros: Highly efficient, and produces clear and maintainable code.
 * Cons: Requires MapStruct dependency and additional configuration for more complex scenarios.
 * 4. Using Java Reflection
 * Reflection can dynamically map fields between classes based on their names.

 * public class ReflectionMapper {
 *     public static <T, U> T map(U source, Class<T> targetClass) throws IllegalAccessException, InstantiationException {
 *         T target = targetClass.newInstance();
 *         for (Field field : source.getClass().getDeclaredFields()) {
 *             field.setAccessible(true);
 *             Field targetField = targetClass.getDeclaredField(field.getName());
 *             targetField.setAccessible(true);
 *             targetField.set(target, field.get(source));
 *         }
 *         return target;
 *     }
 * }
 * Pros: Reduces boilerplate code and can handle mappings for any class with similar fields.
 * Cons: Reflection is slower and bypasses compile-time checking, making it prone to runtime errors.
 * 5. Lombok Annotations with @Builder
 * With Lombok's @Builder annotation, you can create builder patterns that simplify mapping.

 * @Builder
 * public class UserDTO {
 *     private Long id;
 *     private String name;
 *     private String email;
 * }
 *
 * public class UserMapper {
 *     public static UserDTO toDto(User user) {
 *         return UserDTO.builder()
 *                 .id(user.getId())
 *                 .name(user.getName())
 *                 .email(user.getEmail())
 *                 .build();
 *     }
 *
 *     public static User toEntity(UserDTO userDTO) {
 *         return User.builder()
 *                 .id(userDTO.getId())
 *                 .name(userDTO.getName())
 *                 .email(userDTO.getEmail())
 *                 .build();
 *     }
 * }
 * Pros: Less boilerplate code when using @Builder for complex mappings.
 * Cons: Requires Lombok, and may not be as readable for small DTOs.
 * 6. Using BeanUtils.copyProperties from Spring Framework
 * The Spring BeanUtils class has a copyProperties method that can copy properties between two objects.

 * public class UserMapper {
 *     public static User toEntity(UserDTO userDTO) {
 *         User user = new User();
 *         BeanUtils.copyProperties(userDTO, user);
 *         return user;
 *     }
 *
 *     public static UserDTO toDto(User user) {
 *         UserDTO userDTO = new UserDTO();
 *         BeanUtils.copyProperties(user, userDTO);
 *         return userDTO;
 *     }
 * }
 * Pros: Simple and lightweight, no external dependencies required.
 * Cons: Copies fields with matching names only; less control over field transformations.
 * Which Approach to Choose?
 * Simple Projects: Use Manual Mapping or BeanUtils.
 * Medium Complexity Projects: Use ModelMapper or MapStruct for automatic mapping.
 * Complex Projects: Use MapStruct for compile-time checking, custom mappings, and high performance.
 *
 *
 *
 *
 *

 * describe @ mapper(Mapper(componentModel = "spring") in @Mapper(componentModel = "spring")
 * public interface UserMapper {
 *     UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
 *
 *     UserDTO toDto(User user);
 *     User toEntity(UserDTO userDTO);
 * }

 * The @Mapper(componentModel = "spring") annotation is used in MapStruct to automatically generate code for converting between DTOs and Entity classes. Here’s a breakdown of what each part of this annotation and class does:
 *
 * @Mapper(componentModel = "spring")
 * @Mapper: This annotation marks an interface as a MapStruct mapper, indicating that MapStruct should generate an implementation for it.
 * componentModel = "spring": This specifies that the generated mapper should be a Spring bean. By setting componentModel to "spring", MapStruct makes the generated implementation compatible with Spring’s dependency injection. This allows you to autowire the mapper directly in other Spring-managed components (e.g., @Service, @Controller).
 * public interface UserMapper
 * This is the actual interface that defines the mapping between the User and UserDTO classes. MapStruct will generate an implementation of this interface at compile time.
 *
 * UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
 * This line provides a way to get a singleton instance of UserMapper if you are not using Spring for dependency injection. However, when using componentModel = "spring", this line becomes redundant because you can simply autowire UserMapper where needed.
 * Mapping Methods
 * UserDTO toDto(User user);: This method maps a User entity to a UserDTO. MapStruct generates code to handle each field conversion.
 * User toEntity(UserDTO userDTO);: This method maps a UserDTO back to a User entity.
 * Example Usage in Spring
 * Given componentModel = "spring", you can use dependency injection to access UserMapper in a Spring component:
 *

 * @Service
 * public class UserService {
 *
 *     private final UserMapper userMapper;
 *
 *     public UserService(UserMapper userMapper) {
 *         this.userMapper = userMapper;
 *     }
 *
 *     public UserDTO getUserDto(User user) {
 *         return userMapper.toDto(user);
 *     }
 *
 *     public User saveUser(UserDTO userDTO) {
 *         return userMapper.toEntity(userDTO);
 *     }
 * }
 * Summary
 * The @Mapper(componentModel = "spring") annotation:
 *
 * Instructs MapStruct to generate a Spring-compatible implementation of the mapper.
 * Allows you to autowire the mapper in Spring components instead of manually calling Mappers.getMapper(...).
 * Enables clean, easy-to-maintain, and reusable mapping logic between DTOs and Entities.
 * */