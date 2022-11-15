package tripboat.tripboat1.CommunityFile;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import tripboat.tripboat1.Util.DataNotFoundException;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommunityService {

    private final CommunityRepository communityRepository;


    public List<Community> getList() {
        return this.communityRepository.findAll();
    }
    public List<Community> postRegion() {
        return this.communityRepository.findAll();
    }
    public void create(String subject, String content, String region) {
        Community community = new Community();

        community.setSubject(subject);
        community.setContent(content);
        community.setRegion(region);
        community.setCreateDate(LocalDateTime.now());
        this.communityRepository.save(community);
    }

    public Community getCommunity(Integer id) {
        Optional<Community> community = this.communityRepository.findById(id);
        if (community.isPresent()) {
            return community.get();
        } else {
            throw new DataNotFoundException("community not found");
        }
    }


}
