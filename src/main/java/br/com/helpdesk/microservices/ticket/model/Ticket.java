package br.com.helpdesk.microservices.ticket.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.helpdesk.microservices.ticket.enums.TicketStatus;

@Entity
@Table(name = "ticket")
public class Ticket implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Length(min = 25, max = 255)
	private String description;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String applicantUsername;
	
	@Column(nullable = false)
	private String applicantEmail;
	
	@Column
	private String assigneeUsername;
	
	@Column
	private String assigneeEmail;
	
	@Column(nullable = false)
	private Date openedAt;
	
	@Column
	private Date closedAt;
	
	@Column
	private String resolution;
	
	@Column(nullable = false)
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category categoryId;
	
	@JsonSerialize
	@OneToMany(mappedBy = "ticket")
	private List<Commentary> commentaries = new ArrayList<>();
	
	public Ticket() {
	}

	public Ticket(Long id, String description, String title, String applicantUsername, String applicantEmail,
			Date openedAt, Category categoryId, TicketStatus status) {
		super();
		this.id = id;
		this.description = description;
		this.title = title;
		this.applicantUsername = applicantUsername;
		this.applicantEmail = applicantEmail;
		this.openedAt = openedAt;
		this.categoryId = categoryId;
		this.status = (status == null) ? null : status.getCod();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApplicantUsername() {
		return applicantUsername;
	}

	public void setApplicantUsername(String applicantUsername) {
		this.applicantUsername = applicantUsername;
	}

	public String getApplicantEmail() {
		return applicantEmail;
	}

	public void setApplicantEmail(String applicantEmail) {
		this.applicantEmail = applicantEmail;
	}

	public String getAssigneeUsername() {
		return assigneeUsername;
	}

	public void setAssigneeUsername(String assigneeUsername) {
		this.assigneeUsername = assigneeUsername;
	}

	public String getAssigneeEmail() {
		return assigneeEmail;
	}

	public void setAssigneeEmail(String assigneeEmail) {
		this.assigneeEmail = assigneeEmail;
	}

	public Date getOpenedAt() {
		return openedAt;
	}

	public void setOpenedAt(Date openedAt) {
		this.openedAt = openedAt;
	}

	public Date getClosedAt() {
		return closedAt;
	}

	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	public List<Commentary> getCommentaries() {
		return commentaries;
	}

	public void setCommentaries(List<Commentary> commentaries) {
		this.commentaries = commentaries;
	}

	public TicketStatus getStatus() {
		return TicketStatus.toEnum(status);
	}

	public void setStatus(TicketStatus status) {
		this.status = status.getCod();
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicantEmail == null) ? 0 : applicantEmail.hashCode());
		result = prime * result + ((applicantUsername == null) ? 0 : applicantUsername.hashCode());
		result = prime * result + ((assigneeEmail == null) ? 0 : assigneeEmail.hashCode());
		result = prime * result + ((assigneeUsername == null) ? 0 : assigneeUsername.hashCode());
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((closedAt == null) ? 0 : closedAt.hashCode());
		result = prime * result + ((commentaries == null) ? 0 : commentaries.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((openedAt == null) ? 0 : openedAt.hashCode());
		result = prime * result + ((resolution == null) ? 0 : resolution.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (applicantEmail == null) {
			if (other.applicantEmail != null)
				return false;
		} else if (!applicantEmail.equals(other.applicantEmail))
			return false;
		if (applicantUsername == null) {
			if (other.applicantUsername != null)
				return false;
		} else if (!applicantUsername.equals(other.applicantUsername))
			return false;
		if (assigneeEmail == null) {
			if (other.assigneeEmail != null)
				return false;
		} else if (!assigneeEmail.equals(other.assigneeEmail))
			return false;
		if (assigneeUsername == null) {
			if (other.assigneeUsername != null)
				return false;
		} else if (!assigneeUsername.equals(other.assigneeUsername))
			return false;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (closedAt == null) {
			if (other.closedAt != null)
				return false;
		} else if (!closedAt.equals(other.closedAt))
			return false;
		if (commentaries == null) {
			if (other.commentaries != null)
				return false;
		} else if (!commentaries.equals(other.commentaries))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (openedAt == null) {
			if (other.openedAt != null)
				return false;
		} else if (!openedAt.equals(other.openedAt))
			return false;
		if (resolution == null) {
			if (other.resolution != null)
				return false;
		} else if (!resolution.equals(other.resolution))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
