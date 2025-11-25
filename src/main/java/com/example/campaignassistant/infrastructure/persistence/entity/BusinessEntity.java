package com.example.campaignassistant.infrastructure.persistence.entity;

import com.example.campaignassistant.domain.enums.ToneOfVoice;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "businesses")
public class BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String niche;

    @Column(name = "target_audience")
    private String targetAudience;

    @Enumerated(EnumType.STRING)
    @Column(name = "tone_of_voice")
    private ToneOfVoice toneOfVoice;

    private String location;

    @Column(name = "created_at")
    private Instant createdAt;

    public BusinessEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNiche() {
        return niche;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public ToneOfVoice getToneOfVoice() {
        return toneOfVoice;
    }

    public String getLocation() {
        return location;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNiche(String niche) {
        this.niche = niche;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public void setToneOfVoice(ToneOfVoice toneOfVoice) {
        this.toneOfVoice = toneOfVoice;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
