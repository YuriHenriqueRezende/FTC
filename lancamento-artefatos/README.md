# 🎯 Sistema de Lançamento de Artefatos – FIRST Tech Challenge (FTC)

Este documento apresenta os **principais tipos de sistemas de lançamento (shooters)** utilizados no FIRST Tech Challenge (FTC), abordando sua arquitetura mecânica, fundamentos físicos e estratégias de controle para maximizar **precisão, velocidade e consistência de pontuação**.

---

# 🧠 O que é o Sistema de Lançamento (Shooter)

O sistema de lançamento é o subsistema responsável por:

- Armazenar os artefatos (game pieces)  
- Acelerar os artefatos até a velocidade necessária  
- Direcionar o lançamento para o alvo (GOAL)  
- Garantir repetibilidade, estabilidade e precisão nos disparos  

---

# ⚙️ Tipos de Lançadores no FTC

## 🌀 1) Flywheel Shooter (Roda de Inércia)

### Descrição  
Utiliza uma ou mais rodas girando em alta velocidade para lançar o artefato por **atrito controlado**.

### Vantagens  
- Alta cadência de disparo (modo burst)  
- Alta precisão e repetibilidade  
- Consistência em competições  
- Fácil controle de velocidade com PIDF  

### Desvantagens  
- Alto consumo de energia  
- Necessidade de controle preciso de velocidade  
- Tempo de aceleração (spin-up time)  

### Configurações comuns  
- Flywheel simples (uma roda)  
- Dual flywheel (duas rodas para maior potência e estabilidade)  
- Hood ajustável (controle do ângulo de lançamento)  

---

## 🧵 2) Catapult / Lançador de Braço

### Descrição  
Um braço mecânico, acionado por mola ou motor, arremessa o artefato em um movimento rápido.

### Vantagens  
- Alta potência de lançamento  
- Simplicidade mecânica  
- Eficiente para longas distâncias  

### Desvantagens  
- Baixa cadência de disparo  
- Difícil controle fino de precisão  
- Alto desgaste mecânico  

---

## 🧲 3) Puncher / Pusher Shooter (Lançador Linear)

### Descrição  
Um pistão linear empurra o artefato ao longo de um trilho ou canal.

### Vantagens  
- Sistema simples e confiável  
- Fácil controle por servo ou motor linear  
- Boa repetibilidade  

### Desvantagens  
- Alcance limitado  
- Velocidade menor que flywheels  
- Ajuste fino de trajetória mais difícil  

---

## 🧯 4) Pneumatic Launcher (quando permitido pelas regras)

### Descrição  
Utiliza ar comprimido para impulsionar o artefato.

### Vantagens  
- Alta potência  
- Alta repetibilidade  

### Desvantagens  
- Nem sempre permitido nas regras FTC  
- Maior complexidade e peso do sistema  

---

# 🧪 Física do Lançamento

## 📐 Fatores que influenciam a trajetória do tiro

- Velocidade angular da roda (RPM)  
- Ângulo de lançamento (hood angle)  
- Massa e formato do artefato  
- Distância até o alvo  
- Atrito e compressão entre artefato e flywheel  

---

## 📊 Modelo simplificado do Flywheel

O sistema flywheel pode ser aproximado por um modelo de **primeira ordem**:


Onde:  
- ω(s) = velocidade angular do flywheel  
- V(s) = tensão aplicada ao motor  
- K = ganho do sistema  
- τ = constante de tempo (tempo de resposta do sistema)  

---

