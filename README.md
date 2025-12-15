# VitalMetrics — Monitor Avançado de Saúde & Metabolismo

VitalMetrics é um aplicativo Android moderno e orientado à privacidade, criado para auxiliar usuários no acompanhamento preciso de indicadores essenciais de saúde. Desenvolvido integralmente em **Kotlin** com **Jetpack Compose**, o aplicativo vai além do cálculo tradicional de IMC, oferecendo análises metabólicas avançadas como **TMB**, **gasto calórico diário (TDEE)** e **histórico evolutivo**.

O foco do VitalMetrics é entregar uma experiência limpa e intuitiva baseada no **Material Design 3**, garantindo que todos os dados do usuário sejam armazenados exclusivamente no dispositivo, sem qualquer dependência de serviços externos.

---

##  Funcionalidades Principais

### ⚡ Análises de Saúde em Tempo Real
- **IMC Instantâneo:** Cálculo automático com classificação baseada nos padrões da Organização Mundial da Saúde (OMS).
- **Indicadores Metabólicos Avançados:**
  - Taxa Metabólica Basal (TMB) pela equação de Mifflin-St Jeor
  - Gasto Energético Diário Total (TDEE)
  - Peso Ideal pela fórmula de Devine

###  Histórico Evolutivo
- **Armazenamento Local:** Todos os registros são salvos de forma segura em um banco de dados Room local.
- **Exploração Detalhada:** Visualização cronológica dos dados com acesso individual a registros anteriores para análise aprofundada.

---

## 🛠️ Arquitetura & Decisões Técnicas

O VitalMetrics segue práticas modernas de desenvolvimento Android, priorizando organização, escalabilidade e código limpo.

### 1. Arquitetura — MVVM com Fluxo Unidirecional
- **UI (Composables):** Componentes puramente declarativos, responsáveis apenas por renderizar estado e emitir eventos.
- **ViewModel:** Centraliza o estado da aplicação, garantindo resiliência a mudanças de configuração e expondo dados via `StateFlow`.
- **Fluxo Unidirecional:** Eventos sobem, estado desce, mantendo previsibilidade e facilidade de depuração.

### 2. Camada de Domínio Bem Definida
- **Use Cases:** Toda a lógica matemática e regras de negócio são encapsuladas em casos de uso independentes do Android, tornando o código testável e reutilizável.

### 3. Persistência Reativa com Room
- **Modelagem Rica:** Os registros armazenam tanto os dados de entrada quanto os valores calculados, permitindo análises futuras ou recálculos.
- **Atualização Automática da UI:** Uso de `Flow` garante que qualquer alteração no banco reflita instantaneamente na interface.

---

## 🚀 Tecnologias Utilizadas
- **Linguagem:** Kotlin 2.0
- **Interface:** Jetpack Compose (Material Design 3)
- **Navegação:** Navigation Compose
- **Banco de Dados:** Room (SQLite) com KSP
- **Execução em Segundo Plano:** WorkManager
- **Renderização Gráfica:** Canvas nativo do Compose

---

## 🧠 Aprendizados do Projeto
O desenvolvimento do VitalMetrics consolidou conceitos avançados, como:
- Transição de lógica imperativa para um modelo reativo com MVVM.
- Gerenciamento de dependências modernas (Kotlin 2.0 e KSP).
- Implementação de validações rigorosas e boas práticas de programação.

---

## ▶️ Como Executar
1. Clone o repositório do projeto.
2. Abra no Android Studio.
3. Execute em um emulador ou dispositivo físico.
