package com.example.finalwork02.controller;

import com.example.finalwork02.pojo.LearningMaterial;

public class LearningMaterialAndUri {
   private LearningMaterial learningMaterial;
   private String uriLink;

    public LearningMaterial getLearningMaterial() {

        return learningMaterial;
    }

    public void setLearningMaterial(LearningMaterial learningMaterial) {
        this.learningMaterial = learningMaterial;
    }

    public String getUriLink() {
        return uriLink;
    }

    public void setUriLink(String uriLink) {
        this.uriLink = uriLink;
    }
}
