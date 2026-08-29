package com.example.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "traffic_sessions")
data class TrafficSessionEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0L,
  val startTime: Long,
  val endTime: Long,
  val bytesTransferred: Long,
  val earningsUsd: Double
)

@Entity(tableName = "remote_nodes")
data class RemoteNodeEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0L,
  val name: String,
  val ip: String,
  val platform: String,
  val isOnline: Boolean,
  val totalTrafficMb: Double,
  val estimatedEarningsUsd: Double,
  val lastSeenTimestamp: Long
)

@Dao
interface TrafficDao {
  @Query("SELECT * FROM traffic_sessions ORDER BY startTime DESC")
  fun getAllSessions(): Flow<List<TrafficSessionEntity>>

  @Query("SELECT SUM(bytesTransferred) FROM traffic_sessions")
  fun getTotalLifetimeBytes(): Flow<Long?>

  @Query("SELECT SUM(earningsUsd) FROM traffic_sessions")
  fun getTotalLifetimeEarnings(): Flow<Double?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertSession(session: TrafficSessionEntity): Long

  @Query("DELETE FROM traffic_sessions")
  suspend fun clearSessions()
}

@Dao
interface RemoteNodeDao {
  @Query("SELECT * FROM remote_nodes ORDER BY lastSeenTimestamp DESC")
  fun getAllNodes(): Flow<List<RemoteNodeEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertOrUpdateNode(node: RemoteNodeEntity): Long

  @Query("DELETE FROM remote_nodes WHERE id = :id")
  suspend fun deleteNode(id: Long)
}

@Database(
  entities = [TrafficSessionEntity::class, RemoteNodeEntity::class],
  version = 1,
  exportSchema = false
)
abstract class TraffDatabase : RoomDatabase() {
  abstract fun trafficDao(): TrafficDao
  abstract fun remoteNodeDao(): RemoteNodeDao
}
