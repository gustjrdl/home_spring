package tripboat.tripboat1.CommunityFile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@Controller
@Getter
@Setter
public class CommunityController {
    private final CommunityService communityService;

    @RequestMapping("/community")
    private String list(Model model) {
        List<Community> communityList = this.communityService.getList();
        model.addAttribute("communityList",communityList);
        return "CommunityMain";
    }

    @RequestMapping(value = "community/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Community community = this.communityService.getCommunity(id);
        model.addAttribute("community", community);
        return "CommunityContent";
    }

    @GetMapping("/writer")
    public String WriterForm (CommunityForm communityForm){
        return "Writer";
    }

    @PostMapping("/writer")
    public String WriterDate (@Valid CommunityForm communityForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "Writer";
        }
        this.communityService.create(communityForm.getSubject(),communityForm.getContent(),communityForm.getRegion()); {
            return "redirect:/community";
        }
    }


}
