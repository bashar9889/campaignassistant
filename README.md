## AI-Personalized Campaign Assistant for Local SMBs

This project is a small AI-powered campaign assistant inspired by Vendasta’s mission to:
help small and medium businesses get more customers — and keep them.
It’s a Java / Spring Boot service that exposes intelligent, scalable RESTful APIs to:

 - Register a local business (SMB)
 - Create lifecycle rules (welcome, win-back, etc.)
 - Use an AI model to generate personalized email campaigns based on that context

It’s a tiny slice of an AI-first customer acquisition and engagement platform: we embed AI into the core workflow instead of treating it as an add-on.

## Stack

- Java 21, Spring Boot 4 (Web, Data JPA, Validation)
- H2 in-memory DB
- AI layer behind `CampaignCopyGenerator`
  - Real: Hugging Face text-generation model (via Inference API)
  - Mock: local generator for offline use

## Run

```bash
# if you want real AI via Hugging Face
export HUGGINGFACE_API_KEY=hf_your_token_here

mvn spring-boot:run
```
1) Create a Business
```
curl -X POST http://localhost:8080/api/v1/businesses \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Sunrise Bakery",
    "niche": "Bakery / Cafe",
    "targetAudience": "Students and young professionals",
    "toneOfVoice": "CASUAL",
    "location": "Saskatoon, SK"
  }'
```
2) Create Default Lifecycle Rules
```
curl -X POST http://localhost:8080/api/v1/businesses/1/rules/default
```
3) Generate AI Campaigns
```
curl -X POST http://localhost:8080/api/v1/businesses/1/campaigns/generate
```

AI Generated emails for customers inactive for 14 days and 30 days:
<img width="923" height="581" alt="image" src="https://github.com/user-attachments/assets/b7035b61-655f-4f66-9600-aecada8cd551" />

