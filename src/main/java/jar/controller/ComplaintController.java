package jar.controller;

import jar.model.Complaint;
import jar.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @GetMapping
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    @PostMapping
    public Complaint createComplaint(@RequestBody Complaint complaint) {
        return complaintService.saveComplaint(complaint);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Complaint> updateStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam(required = false) String remarks) {
        Complaint updated = complaintService.updateStatus(id, status, remarks);
        return ResponseEntity.ok(updated);
    }
}