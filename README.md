
# Central Sale

This is a simple web application used to track user's favorite Amazon items and the price with it. When running, it'll notify the user via email or on the application itself if an item they have added to their hub has gone on sale or not! 

**Note:** This web application uses an external API to pull Amazon item data called 'Rainforest API' -- You'll have to get your own API key for this.


## Authors

- [@ryandev1004](https://www.github.com/ryandev1004)


## Deployment

To run this project, you'll need to create a application.properties file in your src/main/resources directory. Inside that file you'll need to have something that looks like this inside:

```
spring.application.name=CentralSale
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
rainforest.api.key=${RAINFOREST_API_KEY}

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${TEST_MAIL}
spring.mail.password=${TEST_MAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```
Once you've completed that, you'll need to run both the server via '**CentralSaleApplication**' within the following directory:
```bash
CentralSale/src/main/java/CentralSaleApplication
```
Then, run the frontend by doing:

```bash
  npm run dev
```

**Note:** This has only been tested in  development mode--feel free to use this in a production environment at your own discretion.
