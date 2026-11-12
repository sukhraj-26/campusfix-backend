package jar.service;

import jar.model.Complaint;
import jar.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Complaint saveComplaint(Complaint complaint) {
        if (complaint.getStatus() == null) {
            complaint.setStatus("PENDING");
        }
        return complaintRepository.save(complaint);
    }

    public Complaint updateStatus(Long id, String status, String remarks) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + id));
        complaint.setStatus(status);
        if (remarks != null) {
            complaint.setAdminRemarks(remarks);
        }
        return complaintRepository.save(complaint);
    }
}