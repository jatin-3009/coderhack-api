package com.example.coderhack.service;

import java.util.*;

import com.example.coderhack.dto.Badge;
import com.example.coderhack.dto.ScoreRequestDto;
import com.example.coderhack.dto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coderhack.entity.UserEntity;
import com.example.coderhack.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        mergeSort(userEntities, 0, userEntities.size() - 1);
        return userEntities;
    }

    @Override
    public Optional<UserEntity> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public String addUser(UserRequestDto userRequestDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequestDTO.getUsername());
        userEntity.setScore(0);
        userEntity.setBadges(new HashSet<>());

        UserEntity savedUserEntity = userRepository.save(userEntity);
        return savedUserEntity.getId();
    }

    @Override
    public String updateUserById(String id, ScoreRequestDto scoreRequestDto) throws Exception {
        Optional<UserEntity> optionalUserEntity = getUserById(id);

        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            Integer score = scoreRequestDto.getScore();
            Set<Badge> badges = userEntity.getBadges();

            Badge awardedBadge = getAwardedBadgeBasedOnScore(score);
            badges.add(awardedBadge);

            userEntity.setScore(score);
            userEntity.setBadges(badges);
            userRepository.save(userEntity);

            return id;
        } else {
            throw new Exception("User not present with the id: " + id);
        }
    }

    @Override
    public String deleteUserById(String id) {
        userRepository.deleteById(id);
        return id;
    }

    private Badge getAwardedBadgeBasedOnScore(Integer score) {
        if (score >= 1 && score < 30) {
            return Badge.CODE_NINJA;
        } else if (score >= 30 && score < 60) {
            return Badge.CODE_CHAMP;
        } else {
            return Badge.CODE_MASTER;
        }
    }

    private void mergeSort(List<UserEntity> userEntities, int beg, int end) {
        if (beg < end) {
            int mid = (beg + end) / 2;
            mergeSort(userEntities, beg, mid);
            mergeSort(userEntities, mid + 1, end);
            merge(userEntities, beg, mid, end);
        }
    }

    private void merge(List<UserEntity> userEntities, int beg, int mid, int end) {
        List<UserEntity> combined = new ArrayList<>();
        int p1 = beg;
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= end) {
            if (userEntities.get(p1).getScore() < userEntities.get(p2).getScore()) {
                combined.add(userEntities.get(p1++));
            } else {
                combined.add(userEntities.get(p2++));
            }
        }

        while (p1 <= mid) {
            combined.add(userEntities.get(p1++));
        }

        while (p2 <= end) {
            combined.add(userEntities.get(p2++));
        }

        for (int i = beg; i <= end; i++) {
            userEntities.set(i, combined.get(i));
        }
    }
}
