# AI-Personalized Campaign Assistant

Small Spring Boot service that lets a local SMB:
1. Register their business.
2. Create default lifecycle rules (welcome, win-back).
3. Generate AI-written email campaigns (subject + body) from those rules.

It’s meant to demo an **AI-first, RESTful service** similar to a tiny slice of Vendasta’s platform.

## Stack

- Java 17, Spring Boot 3 (Web, Data JPA, Validation)
- H2 in-memory DB
- AI layer behind `CampaignCopyGenerator`
  - Real: Hugging Face text-generation model (via Inference API)
  - Mock: local generator for offline/demo use

## Run

```bash
# (optional) if you want real AI via Hugging Face
export HUGGINGFACE_API_KEY=hf_your_token_here

mvn spring-boot:run
