package com.application.matching.controller;

import com.application.matching.model.MatchingResult;
import com.application.matching.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class MatchingController {

    @Autowired
    MatchingService matchingService;


    @PostMapping("/api/matching/upload")
    public String uploadFileAndGetResult(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("status", false);
            model.addAttribute("message", "Please select a file to upload.");
        } else {
            MatchingResult matchingResult = null;
            try {
                matchingResult = matchingService.getMatchingResult(file);
            } catch (IOException e) {
               model.addAttribute("status", false);
               model.addAttribute("message", e.getMessage());
            }
            if (matchingResult != null) {
                model.addAttribute("result", matchingResult);
                model.addAttribute("status", true);
            }
        }
        return "matchingResult";
    }
}
