
const baseUrl = 'http://localhost:8080/PCs/';
const pcList = document.getElementById('pc-list');
const pcForm = document.getElementById('pc-form');
const productIdInput = document.getElementById('product-id');
const pcIdInput = document.getElementById('pc-id');
const pcNameInput = document.getElementById('pc-name');
const energyClassInput = document.getElementById('energy-class');
const dimensionInput = document.getElementById('dimension');
const lifecycleInput = document.getElementById('lifecycle');
const powerRatingInput = document.getElementById('power-rating');
const installingInstructionsInput = document.getElementById('installing-instructions');
const maintenanceInstructionsInput = document.getElementById('maintenance-instructions');
const repairInstructionsInput = document.getElementById('repair-instructions');
const assemblyCarbonFootprintInput = document.getElementById('assembly-carbon-footprint');
const warrantyInput = document.getElementById('warranty');
const componentTypeInput = document.getElementById('component-type');
const componentIdInput = document.getElementById('component-id');
const componentNameInput = document.getElementById('component-name');

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
        <td>${pc.pcName}</td>
        <td>${pc.productId}</td>
        <td>${pc.energyClass || '-'}</td>
        <td>${pc.powerRating || '-'}</td>
        <td>${pc.dimension || '-'}</td>
        <td>${pc.lifecycle || '-'}</td>
        <td>${pc.installingInstructions || '-'}</td>
        <td>${pc.maintenanceInstructions || '-'}</td>
        <td>${pc.repairInstructions || '-'}</td>
        <td>${pc.assemblyCarbonFootprint || '-'}</td>
        <td>${pc.warranty || '-'}</td>
        <td>${pc.componentType || '-'}</td>
        <td>${pc.componentId || '-'}</td>
        <td>${pc.componentName || '-'}</td>
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
        pcIdd: pcIdInput.value.trim() || null,
        pcName: pcNameInput.value.trim() || null,
        productId: productIdInput.value.trim() || null,
        energyClass: energyClassInput.value.trim() || null,
        dimension: dimensionInput.value.trim() || null,
        lifecycle: lifecycleInput.value.trim() || null,
        powerRating: powerRatingInput.value ? parseInt(powerRatingInput.value) : null,
        installingInstructions: installingInstructionsInput.value.trim() || null,
        maintenanceInstructions: maintenanceInstructionsInput.value.trim() || null,
        repairInstructions: repairInstructionsInput.value.trim() || null,
        assemblyCarbonFootprint: assemblyCarbonFootprintInput.value.trim() || null,
        warranty: warrantyInput.value.trim() || null,
        componentType: componentTypeInput.value.trim() || null,
        componentId: componentIdInput.value.trim() || null,
        componentName: componentNameInput.value.trim() || null
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
        pcNameInput.value = pc.pcName;
        productIdInput.value = productIdInput;
        energyClassInput.value = pc.energyClass || '';
        dimensionInput.value = pc.dimension || '';
        lifecycleInput.value = pc.lifecycle || '';
        powerRatingInput.value = pc.powerRating || '';
        installingInstructionsInput.value = pc.installingInstructions || '';
        maintenanceInstructionsInput.value = pc.maintenanceInstructions || '';
        repairInstructionsInput.value = pc.repairInstructions || '';
        assemblyCarbonFootprintInput.value = pc.assemblyCarbonFootprint || '';
        warrantyInput.value = pc.warranty || '';
        componentTypeInput.value = pc.componentType || '';
        componentIdInput.value = pc.componentId || '';
        componentNameInput.value = pc.componentName || '';

    } catch (error) {
        console.error("Error loading PC:", error);
    }
}

fetchPCs();
