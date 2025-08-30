package com.parkjh.where_is.service;

import com.parkjh.where_is.domain.User;
import com.parkjh.where_is.dto.UserRequestDto;
import com.parkjh.where_is.dto.UserResponseDto;
import com.parkjh.where_is.dto.UserSearchDto;
import com.parkjh.where_is.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getList(UserSearchDto query) {
        Sort sort = "desc".equalsIgnoreCase(query.getOrder())
                ? Sort.by(query.getSort()).descending()
                : Sort.by(query.getSort()).ascending();

        PageRequest pageRequest = PageRequest.of(
                (int) query.getPage(),
                (int) query.getSize(),
                sort
        );
        // 검색 조건이 있는 경우 동적 쿼리 생성
        if (hasSearchCriteria(query)) {
            return userRepository.findUsersWithSearchCriteria(
                    query.getName(),
                    query.getPhone(),
                    query.getNickname(),
                    query.getEmail(),
                    query.getBirthday(),
                    pageRequest
            );
        }
        // 검색 조건이 없는 경우 전체 조회
        return userRepository.findAll(pageRequest);
    }

    public Optional<User> getOne(Long userId) {
        return userRepository.findById(userId);
    }

    private boolean hasSearchCriteria(UserSearchDto query) {
        return (query.getName() != null && !query.getName().trim().isEmpty()) ||
               (query.getPhone() != null && !query.getPhone().trim().isEmpty()) ||
               (query.getNickname() != null && !query.getNickname().trim().isEmpty()) ||
               (query.getEmail() != null && !query.getEmail().trim().isEmpty()) ||
               (query.getBirthday() != null && !query.getBirthday().toString().trim().isEmpty());
    }

    public UserResponseDto save(UserRequestDto request) {
        User createUser = userRepository.save(request.toEntity());
        return UserResponseDto.toResponseDto(createUser);
    }

    public UserResponseDto update(Long id, UserRequestDto userRequest) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        
        // 부분 업데이트: null이 아닌 값만 업데이트
        if (userRequest.getName() != null) {
            existingUser.setName(userRequest.getName());
        }
        if (userRequest.getEmail() != null) {
            existingUser.setEmail(userRequest.getEmail());
        }
        if (userRequest.getPhone() != null) {
            existingUser.setPhone(userRequest.getPhone());
        }
        if (userRequest.getPw() != null) {
            existingUser.setPw(userRequest.getPw());
        }
        // roleId는 int 타입이므로 기본값 0과 비교
        if (userRequest.getRoleId() != 0) {
            existingUser.setRoleId(userRequest.getRoleId());
        }
        if (userRequest.getNickname() != null) {
            existingUser.setNickname(userRequest.getNickname());
        }
        if (userRequest.getProfileImag() != null) {
            existingUser.setProfileImag(userRequest.getProfileImag());
        }
        if (userRequest.getBirthday() != null) {
            existingUser.setBirthday(userRequest.getBirthday());
        }
        
        User updatedUser = userRepository.save(existingUser);
        return UserResponseDto.toResponseDto(updatedUser);
    }

    public Map<String, Object> delete(List<Long> ids) {
        // 삭제 전에 존재하는 ID 확인
        List<Long> existingIds = userRepository.findAllById(ids)
                .stream()
                .map(User::getId)
                .toList();

        userRepository.deleteAllById(existingIds); // 실제 삭제 실행

        // 결과 반환
        Map<String, Object> result = new HashMap<>();
        result.put("requestedCount", ids.size());       // 요청한 개수
        result.put("foundCount", existingIds.size());   // 실제 존재했던 개수
        result.put("deletedIds", existingIds);          // 삭제된 ID 목록
        result.put("notFoundIds", ids.stream()
                .filter(id -> !existingIds.contains(id))
                .toList());        // 존재하지 않았던 ID 목록

        return result;
    }


    public User validateLogin(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user == null) return null;

        boolean passwordValidate = password.equals(user.getPw());
        if (! passwordValidate) return null;
        else return user;
    }
}