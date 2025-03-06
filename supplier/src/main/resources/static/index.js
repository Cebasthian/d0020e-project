const gpuApiUrl = "http://localhost:8083/gpus";
const materialApiUrl = "http://localhost:8083/materials";
let materialArray = [];

document
  .getElementById("materialForm")
  .addEventListener("submit", async (e) => {
    e.preventDefault();

    const materialData = {
      materialType: document.getElementById("newMaterialType").value,
      origin: document.getElementById("newOrigin").value,
      materialFlow: document.getElementById("newMaterialFlow").value,
      supplier: document.getElementById("newSupplier").value,
    };

    try {
      const response = await fetch(materialApiUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(materialData),
      });

      if (!response.ok) {
        const error = await response.json();
        throw new Error(error.message || "Failed to create material");
      }

      const result = await response.json();
      alert(`Material created successfully! ID: ${result.id}`);
      document.getElementById("materialForm").reset();
      loadMaterials();
    } catch (error) {
      console.error("Error creating material:", error);
      alert(`Error: ${error.message}`);
    }
  });

document.getElementById("addMaterialBtn").addEventListener("click", () => {
  const materialId = document.getElementById("materialId").value;
  const materialType = document.getElementById("materialType").value.trim();
  const origin = document.getElementById("origin").value.trim();
  const materialFlow = document.getElementById("materialFlow").value.trim();
  const supplier = document.getElementById("supplier").value.trim();

  if (materialId && isNaN(materialId)) {
    alert("Material ID must be a number");
    return;
  }

  if (!materialId) {
    if (!materialType || !origin || !materialFlow || !supplier) {
      alert("All material fields are required for new materials!");
      return;
    }
    materialArray.push({
      materialType,
      origin,
      materialFlow,
      supplier,
    });
  } else {
    materialArray.push({
      id: parseInt(materialId),
    });
  }

  updateMaterialsDisplay();
  clearMaterialInputs();
});

document.getElementById("gpuForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const gpuId = document.getElementById("gpuId").value;
  const method = gpuId ? "PUT" : "POST";
  const url = gpuId ? `${gpuApiUrl}/${gpuId}` : gpuApiUrl;

  const processedMaterials = materialArray.map((material) => {
    if (material.id) return { id: Number(material.id) };
    return material;
  });

  const gpuData = {
    componentId: document.getElementById("componentId").value,
    name: document.getElementById("name").value,
    weight: parseInt(document.getElementById("weight").value),
    dimensions: document.getElementById("dimensions").value,
    powerRating: parseInt(document.getElementById("powerRating").value),
    installingInstructions: document.getElementById("installingInstructions")
      .value,
    assemblyLine: document.getElementById("assemblyLine").value,
    materials: processedMaterials,
  };

  try {
    const response = await fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(gpuData),
    });

    const responseBody = await response.json();

    if (!response.ok) {
      throw new Error(
        responseBody.message ||
          responseBody.error ||
          `HTTP error! status: ${response.status}`
      );
    }

    alert(
      `GPU ${gpuId ? "updated" : "created"} successfully! ID: ${
        responseBody.id
      }`
    );
    resetGpuForm();
    loadGpus();
    loadMaterials();
  } catch (error) {
    console.error("Error:", error);
    alert(`Operation failed: ${error.message}`);
  }
});

document
  .getElementById("editMaterialForm")
  .addEventListener("submit", async (e) => {
    e.preventDefault();

    const materialId = document.getElementById("editMaterialId").value;
    const materialData = {
      materialType: document.getElementById("editMaterialType").value,
      origin: document.getElementById("editOrigin").value,
      materialFlow: document.getElementById("editMaterialFlow").value,
      supplier: document.getElementById("editSupplier").value,
    };

    try {
      const response = await fetch(`${materialApiUrl}/${materialId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(materialData),
      });

      if (!response.ok) throw new Error("Update failed");
      alert("Material updated successfully!");
      loadMaterials();
      document.getElementById("editMaterialForm").reset();
    } catch (error) {
      console.error("Error updating material:", error);
      alert(`Error: ${error.message}`);
    }
  });

async function editGpu(id) {
  try {
    const response = await fetch(`${gpuApiUrl}/${id}`);
    const gpu = await response.json();

    document.getElementById("gpuId").value = gpu.id;
    document.getElementById("componentId").value = gpu.componentId;
    document.getElementById("name").value = gpu.name;
    document.getElementById("weight").value = gpu.weight;
    document.getElementById("dimensions").value = gpu.dimensions;
    document.getElementById("powerRating").value = gpu.powerRating;
    document.getElementById("installingInstructions").value =
      gpu.installingInstructions;
    document.getElementById("assemblyLine").value = gpu.assemblyLine;

    materialArray = gpu.materials;
    updateMaterialsDisplay();

    document.getElementById("gpuFormTitle").textContent = `Edit GPU ${id}`;
    document.getElementById("gpuSubmitBtn").textContent = "Update GPU";
    document.getElementById("cancelEditBtn").style.display = "inline-block";
  } catch (error) {
    console.error("Error loading GPU:", error);
  }
}

async function editMaterial(id) {
  try {
    const response = await fetch(`${materialApiUrl}/${id}`);
    const material = await response.json();

    document.getElementById("editMaterialId").value = material.id;
    document.getElementById("editMaterialType").value = material.materialType;
    document.getElementById("editOrigin").value = material.origin;
    document.getElementById("editMaterialFlow").value = material.materialFlow;
    document.getElementById("editSupplier").value = material.supplier;
  } catch (error) {
    console.error("Error loading Material:", error);
  }
}

async function deleteGpu(id) {
  if (confirm(`Delete GPU ${id}?`)) {
    try {
      await fetch(`${gpuApiUrl}/${id}`, { method: "DELETE" });
      loadGpus();
    } catch (error) {
      console.error("Error deleting GPU:", error);
    }
  }
}

async function deleteMaterial(id) {
  if (confirm(`Delete Material ${id}?`)) {
    try {
      await fetch(`${materialApiUrl}/${id}`, { method: "DELETE" });
      loadMaterials();
    } catch (error) {
      console.error("Error deleting Material:", error);
    }
  }
}

function renderGpu(gpu) {
  return `
    <div class="gpu-item">
        <h3>${gpu.name} (ID: ${gpu.id})</h3>
        <p><strong>Component ID:</strong> ${gpu.componentId}</p>
        <p><strong>Weight:</strong> ${gpu.weight}g</p>
        <p><strong>Dimensions:</strong> ${gpu.dimensions}</p>
        <p><strong>Power Rating:</strong> ${gpu.powerRating}W</p>
        <p><strong>Assembly Line:</strong> ${gpu.assemblyLine}</p>
        <p><strong>Instructions:</strong> ${gpu.installingInstructions}</p>
        <h4>Materials:</h4>
        ${
          gpu.materials
            ?.map(
              (material) => `
            <div class="nested-material">
                <p><strong>${material.materialType}</strong> (ID: ${material.id})</p>
                <p>Origin: ${material.origin}</p>
                <p>Flow: ${material.materialFlow}</p>
                <p>Supplier: ${material.supplier}</p>
            </div>
        `
            )
            .join("") || "No materials"
        }
        <button onclick="editGpu(${gpu.id})">Edit</button>
        <button onclick="deleteGpu(${gpu.id})">Delete</button>
    </div>`;
}

function renderMaterial(material) {
  return `
    <div class="material-item">
        <h3>${material.materialType} (ID: ${material.id})</h3>
        <p><strong>Origin:</strong> ${material.origin}</p>
        <p><strong>Flow:</strong> ${material.materialFlow}</p>
        <p><strong>Supplier:</strong> ${material.supplier}</p>
        <button onclick="editMaterial(${material.id})">Edit</button>
        <button onclick="deleteMaterial(${material.id})">Delete</button>
    </div>`;
}

function updateMaterialsDisplay() {
  const display = document.getElementById("materialsDisplay");
  display.innerHTML = materialArray
    .map(
      (material, index) => `
        <li class="nested-material">
            ${
              material.id
                ? `Existing Material (ID: ${material.id})`
                : `New Material: ${material.materialType}`
            }
            <button onclick="removeMaterial(${index})">Remove</button>
        </li>
    `
    )
    .join("");
}

function clearMaterialInputs() {
  document.getElementById("materialId").value = "";
  document.getElementById("materialType").value = "";
  document.getElementById("origin").value = "";
  document.getElementById("materialFlow").value = "";
  document.getElementById("supplier").value = "";
}

function removeMaterial(index) {
  materialArray.splice(index, 1);
  updateMaterialsDisplay();
}

function resetGpuForm() {
  document.getElementById("gpuForm").reset();
  document.getElementById("gpuId").value = "";
  materialArray = [];
  updateMaterialsDisplay();
  document.getElementById("gpuFormTitle").textContent = "Create New GPU";
  document.getElementById("gpuSubmitBtn").textContent = "Create GPU";
  document.getElementById("cancelEditBtn").style.display = "none";
}

async function loadGpus() {
  try {
    const response = await fetch(gpuApiUrl);
    const data = await response.json();
    document.getElementById("gpuList").innerHTML = data.map(renderGpu).join("");
  } catch (error) {
    console.error("Error loading GPUs:", error);
  }
}

async function loadMaterials() {
  try {
    const response = await fetch(materialApiUrl);
    const data = await response.json();
    document.getElementById("materialList").innerHTML = data
      .map(renderMaterial)
      .join("");
  } catch (error) {
    console.error("Error loading Materials:", error);
  }
}

document.getElementById("listGpusBtn").addEventListener("click", loadGpus);
document
  .getElementById("listMaterialsBtn")
  .addEventListener("click", loadMaterials);
document
  .getElementById("cancelEditBtn")
  .addEventListener("click", resetGpuForm);

window.onload = () => {
  loadGpus();
  loadMaterials();
};
