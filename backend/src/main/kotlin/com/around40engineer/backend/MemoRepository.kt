package com.around40engineer.backend

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import org.springframework.stereotype.Repository

interface MemoRepository {
    fun findAll(): List<MemoEntity>
    fun save(memo: MemoEntity)
}

@Repository
class MemoRepositoryImpl(
    private val dynamoDBMapper: DynamoDBMapper
) : MemoRepository {
    override fun findAll(): List<MemoEntity> {
        return dynamoDBMapper.scan(MemoEntity::class.java, DynamoDBScanExpression())
    }

    override fun save(memo: MemoEntity) {
        dynamoDBMapper.save(memo)
    }
}
