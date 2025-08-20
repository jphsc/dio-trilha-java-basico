package br.com.rhscdeveloper.persistence.entity;

import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class Block {

	private long id;
	private OffsetDateTime blockedAt;
	private String blockReason;
	private OffsetDateTime unblockedAt;
	private String unblokReason;
	private Cards cardId;
	
	public Block() {
		
	}

	public Block(OffsetDateTime blockedAt, String blockReason, OffsetDateTime unblockedAt, String unblokReason,
			Cards cardId) {
		this.blockedAt = blockedAt;
		this.blockReason = blockReason;
		this.unblockedAt = unblockedAt;
		this.unblokReason = unblokReason;
		this.cardId = cardId;
	}

	public Block(long id, OffsetDateTime blockedAt, String blockReason, OffsetDateTime unblockedAt, String unblokReason,
			Cards cardId) {
		super();
		this.id = id;
		this.blockedAt = blockedAt;
		this.blockReason = blockReason;
		this.unblockedAt = unblockedAt;
		this.unblokReason = unblokReason;
		this.cardId = cardId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OffsetDateTime getBlockedAt() {
		return blockedAt;
	}

	public void setBlockedAt(OffsetDateTime blockedAt) {
		this.blockedAt = blockedAt;
	}

	public String getBlockReason() {
		return blockReason;
	}

	public void setBlockReason(String blockReason) {
		this.blockReason = blockReason;
	}

	public OffsetDateTime getUnblockedAt() {
		return unblockedAt;
	}

	public void setUnblockedAt(OffsetDateTime unblockedAt) {
		this.unblockedAt = unblockedAt;
	}

	public String getUnblokReason() {
		return unblokReason;
	}

	public void setUnblokReason(String unblokReason) {
		this.unblokReason = unblokReason;
	}

	public Cards getCardId() {
		return cardId;
	}

	public void setCardId(Cards cardId) {
		this.cardId = cardId;
	}
	
}
