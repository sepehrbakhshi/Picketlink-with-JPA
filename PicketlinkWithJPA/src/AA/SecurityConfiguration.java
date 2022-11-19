
package AA;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;


@ApplicationScoped
public class SecurityConfiguration {

    public void onInit(@Observes SecurityConfigurationEvent event) {
        SecurityConfigurationBuilder builder = event.getBuilder();

        builder.idmConfig()
            .named("default")
                .stores()
                    .jpa()
                        .supportAllFeatures();
    }

}
