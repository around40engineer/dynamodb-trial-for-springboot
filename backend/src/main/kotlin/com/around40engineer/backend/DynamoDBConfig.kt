package com.around40engineer.backend

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DynamoDBConfig {
    @Value("\${cloud.aws.region.static}")
    private val awsRegion: String? = null

    @Value("\${cloud.aws.dynamodb.endpoint}")
    private val dynamodbEndpoint: String? = null

    @Bean
    fun createDynamoDBMapper(): DynamoDBMapper {
        return DynamoDBMapper(buildDynamoDB())
    }

    fun buildDynamoDB(): AmazonDynamoDB {
        return AmazonDynamoDBClientBuilder
            .standard()
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(dynamodbEndpoint, awsRegion)
            )
            .build()
    }
}