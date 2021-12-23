package com.blog.socialshare.Controller;

import com.blog.socialshare.Model.Post;
import com.blog.socialshare.Service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping(path = "newpost")
    public String newPost() {
        return "newpost";
    }

    @PostMapping(path = "newpost/save")
    public String savePost(@ModelAttribute("post") Post post, @RequestParam("tag") String tag, Model model) {
        model.addAttribute("postSaved", true);
        String[] tags = tag.split("\n");
        Post savedPost = postService.savePost(post);
        return "redirect:/";
    }

    @GetMapping(path = "updatepost/{postId}")
    public String updatePost(@PathVariable("postId") Integer postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        return "updatepost";
    }

    @PostMapping(path = "updatepost/update")
    public String updatePost(@ModelAttribute("post") Post post, Model model) {
        postService.updatePost(post);
        return "redirect:/blog/" + post.getId();
    }

}