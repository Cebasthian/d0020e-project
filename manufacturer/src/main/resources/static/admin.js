
const baseUrl = 'http://localhost:8080/PCs/';
const pcList = document.getElementById('pc-list');
const pcForm = document.getElementById('pc-form');
const pcIdInput = document.getElementById('pc-id');
const pcNameInput = document.getElementById('pc-name');
const energyClassInput = document.getElementById('energy-class');
const powerRatingInput = document.getElementById('power-rating');

// Fetch all PCs and update the table
async function fetchPCs() {
    try {
        const response = await fetch(baseUrl + 'GET-All-PCs');
        const pcs = await response.json();
        pcList.innerHTML = '';
        pcs.forEach(pc => {
            const row = document.createElement('tr');
            row.innerHTML = `
        <td>${pc.id}</td>
        <td>${pc.productId}</td>
        <td>${pc.energyClass || '-'}</td>
        <td>${pc.powerRating || '-'}</td>
        <td>
          <button class="edit-btn" onclick="loadPC(${pc.id})">Edit</button>
          <button class="delete-btn" onclick="deletePC(${pc.id})">Delete</button>
        </td>
      `;
            pcList.appendChild(row);
        });
    } catch (error) {
        console.error("Error fetching PCs:", error);
    }
}
// Add a new PC and Update
pcForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const pcId = pcIdInput.value.trim();
    const pc = {
        productId: pcNameInput.value.trim() || null,
        energyClass: energyClassInput.value.trim() || null,
        powerRating: powerRatingInput.value ? parseInt(powerRatingInput.value) : null
    };

    const method = pcId ? 'PUT' : 'POST';
    const endpoint = pcId ? `update-pc/${pcId}` : 'CREATE_PCs';  // Corrected endpoint

    try {
        const response = await fetch(baseUrl + endpoint, {  // Only baseUrl once
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(pc)
        });

        if (!response.ok) {
            console.error("Failed to submit PC:", response.statusText);
            return;
        }

        pcForm.reset();
        fetchPCs();
    } catch (error) {
        console.error("Error submitting PC:", error);
    }
});


async function deletePC(id) {
    try {
        await fetch(baseUrl + `delete/${id}`, { method: 'DELETE' });
        fetchPCs();
    } catch (error) {
        console.error("Error deleting PC:", error);
    }
}

async function loadPC(id) {
    try {
        const response = await fetch(baseUrl + `GET/${id}`);
        if (!response.ok) throw new Error("PC not found");
        const pc = await response.json();

        pcIdInput.value = pc.id;
        pcNameInput.value = pc.productId;
        energyClassInput.value = pc.energyClass || '';
        powerRatingInput.value = pc.powerRating || '';
    } catch (error) {
        console.error("Error loading PC:", error);
    }
}

fetchPCs();
