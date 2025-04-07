# 개인 사이드 프로젝트들 모아놓은 리포지토리입니다.

영단어 외우기 사이트, 야구선수 스탯 계산, 로또 번호 예측등 학습 목적으로 개발한것들 모음입니다.

## 프로젝트 사용 기술 스택

Spring boot, JPA, postgresql

## 모니터링 툴 (prometheus, promtail, loki, grafana)
### 서버 로그나 현재 서버 상태 파악 가능한 모니터링 툴 적용
![image](https://github.com/sky7214sky72/portfolio/assets/45224987/1c4e01ac-ba48-4b4a-b740-8106ac23785b)
![image](https://github.com/sky7214sky72/portfolio/assets/45224987/e327c16b-4c5b-4670-b6c9-4aade9f88ba2)


## 프로젝트 다이어그램
```mermaid
flowchart TD
    %% Global Nodes
    Client("Client (User)"):::external
    API("PortfolioApplication"):::entry
    Monitoring("Monitoring Tools (Prometheus, Grafana, Loki)"):::monitoring
    DB("Database (PostgreSQL / H2)"):::db
    DevOps("DevOps/Deployment"):::devops
    Global("Global Config & Security<br/>(Config, Exception, JWT)"):::global

    %% Entry Relationships
    Client -->|"request"| API
    API -->|"initializes"| Global

    %% Word Module Subgraph
    subgraph "Word Module"
        WordCtrl("WordController"):::controller
        WordServ("WordService"):::service
        WordRepo("WordRepository"):::repository
        WordDom("Word Domain"):::domain
    end
    API -->|"routes"| WordCtrl
    WordCtrl -->|"calls"| WordServ
    WordServ -->|"persists via"| WordRepo
    WordServ -->|"uses"| WordDom
    WordRepo -->|"reads/writes"| DB

    %% Baseball Module Subgraph
    subgraph "Baseball Module"
        BaseCtrl("BaseballController"):::controller
        StatServ("StatService"):::service
        BaseRepo("BaseballRepository"):::repository
        BaseDom("Baseball Domain<br/>(OpsCalculator, WrcCalculator)"):::domain
    end
    API -->|"routes"| BaseCtrl
    BaseCtrl -->|"calls"| StatServ
    StatServ -->|"persists via"| BaseRepo
    StatServ -->|"calculates with"| BaseDom
    BaseRepo -->|"reads/writes"| DB

    %% Lotto Module Subgraph
    subgraph "Lotto Module"
        LottoCtrl("LottoController"):::controller
        LottoServ("LottoService"):::service
        LottoRepo("LottoRepository"):::repository
        LottoSched("Lotto Scheduler"):::service
        LottoDom("Lotto Domain"):::domain
    end
    API -->|"routes"| LottoCtrl
    LottoCtrl -->|"calls"| LottoServ
    LottoServ -->|"persists via"| LottoRepo
    LottoServ -->|"schedules"| LottoSched
    LottoServ -->|"processes"| LottoDom
    LottoRepo -->|"reads/writes"| DB

    %% Sign Module Subgraph
    subgraph "Sign Module"
        SignCtrl("SignController"):::controller
        SignServ("SignService"):::service
        SignRepo("SignRepository"):::repository
        OAuthJWT("OAuth/JWT"):::security
        OAuthProv("OAuth Providers<br/>(Kakao, Naver, Google)"):::external
    end
    API -->|"routes"| SignCtrl
    SignCtrl -->|"calls"| SignServ
    SignServ -->|"persists via"| SignRepo
    SignServ -->|"handles"| OAuthJWT
    OAuthJWT -->|"integrates with"| OAuthProv
    SignRepo -->|"reads/writes"| DB

    %% Global integration to Modules (cross-cutting concerns)
    Global ---|"applies to"| WordServ
    Global ---|"applies to"| StatServ
    Global ---|"applies to"| LottoServ
    Global ---|"applies to"| SignServ

    %% Monitoring & DevOps relationships
    API ---|"metrics"| Monitoring
    API ---|"CI/CD triggers"| DevOps

    %% Click Events
    click API "https://github.com/sky7214sky72/portfolio/blob/master/src/main/java/org/example/portfolio/PortfolioApplication.java"
    click WordCtrl "https://github.com/sky7214sky72/portfolio/tree/master/src/main/java/org/example/portfolio/word"
    click BaseCtrl "https://github.com/sky7214sky72/portfolio/tree/master/src/main/java/org/example/portfolio/baseball"
    click LottoCtrl "https://github.com/sky7214sky72/portfolio/tree/master/src/main/java/org/example/portfolio/lotto"
    click SignCtrl "https://github.com/sky7214sky72/portfolio/tree/master/src/main/java/org/example/portfolio/sign"
    click Global "https://github.com/sky7214sky72/portfolio/tree/master/src/main/java/org/example/portfolio/global"
    click DevOps "https://github.com/sky7214sky72/portfolio/blob/master/.github"

    %% Styles
    classDef entry fill:#f9f,stroke:#333,stroke-width:2px;
    classDef controller fill:#add8e6,stroke:#000,stroke-width:1px;
    classDef service fill:#90ee90,stroke:#000,stroke-width:1px;
    classDef repository fill:#d3d3d3,stroke:#000,stroke-width:1px;
    classDef domain fill:#ffeb99,stroke:#000,stroke-width:1px;
    classDef global fill:#ffc0cb,stroke:#000,stroke-width:1px;
    classDef security fill:#ff9999,stroke:#000,stroke-width:1px;
    classDef external fill:#e6e6fa,stroke:#000,stroke-width:1px;
    classDef monitoring fill:#dda0dd,stroke:#000,stroke-width:1px;
    classDef db fill:#ffe4e1,stroke:#000,stroke-width:1px,stroke-dasharray: 4 2;
    classDef devops fill:#f0e68c,stroke:#000,stroke-width:1px;
