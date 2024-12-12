package com.binaries.clutch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.saml2.provider.service.registration.InMemoryRelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistration;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class IdentityProviderConfig {

    private final Environment env;
    private final RelyingPartyRegistrationRepository relyingPartyRegistrationRepository;

    public IdentityProviderConfig(Environment env) {
        this.env = env;
        this.relyingPartyRegistrationRepository = createRelyingPartyRegistrationRepository();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String identityProvider = env.getProperty("identity.provider", "keycloak");

        switch (identityProvider.toLowerCase()) {
            case "saml":
                configureSaml(http);
                break;
            case "keycloak":
                configureOidc(http, "keycloak");
                break;
            case "azure":
                configureOidc(http, "azure");
                break;
            case "aws":
                configureOidc(http, "aws");
                break;
            case "okta":
                configureOidc(http, "okta");
                break;
            default:
                throw new IllegalArgumentException("Unsupported identity provider: " + identityProvider);
        }
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/public/**")
                .permitAll()
                .requestMatchers("/actuator/**")
                .permitAll()
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")
                .anyRequest().authenticated()
        );
        return http.build();
    }

    private void configureOidc(HttpSecurity http, String provider) throws Exception {
        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId(provider)
                .clientId(env.getProperty("identity." + provider + ".client-id"))
                .clientSecret(env.getProperty("identity." + provider + ".client-secret"))
                .issuerUri(env.getProperty("identity." + provider + ".issuer-uri"))
                .authorizationUri(env.getProperty("identity." + provider + ".authorization-uri"))
                .tokenUri(env.getProperty("identity." + provider + ".token-uri"))
                .userInfoUri(env.getProperty("identity." + provider + ".userinfo-uri"))
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .build();
        ClientRegistrationRepository repository = new InMemoryClientRegistrationRepository(clientRegistration);
        http.oauth2Login().clientRegistrationRepository(repository);
    }

    private void configureSaml(HttpSecurity http) throws Exception {
        http.saml2Login(saml2Login -> saml2Login.relyingPartyRegistrationRepository(relyingPartyRegistrationRepository));
    }

    private RelyingPartyRegistrationRepository createRelyingPartyRegistrationRepository() {
        return new InMemoryRelyingPartyRegistrationRepository(
                RelyingPartyRegistration.withRegistrationId("saml").assertingPartyDetails(party ->
                        party.entityId(env.getProperty("identity.saml.metadata-url"))
                                .singleSignOnServiceLocation(env.getProperty("identity.saml.sso-url"))
                ).build());
    }
}
