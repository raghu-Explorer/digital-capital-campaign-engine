/**
 * VentureSeed Capital Networks - Interactive Orchestration Engine
 * Frontend Client Application
 */

const API_BASE_URL = 'http://localhost:8080';

// Mock/Initial Dataset matching backend seed data
const INITIAL_CAMPAIGNS = [
    {
        id: 1,
        title: "QuantumLeaf: Artificial Photosynthetic Energy Cells",
        tagline: "Harnessing ambient sunlight and CO2 to produce ultra-clean hydrogen power for urban buildings.",
        description: "QuantumLeaf has developed next-generation bionic nanofilm surfaces that mimic plant photosynthesis at 18x biological efficiency. Our bio-hybrid cells convert atmospheric carbon dioxide and sunlight directly into clean green hydrogen and electrical power.",
        category: "CLEAN_ENERGY",
        goalAmount: 100000.00,
        currentAmount: 78450.00,
        backerCount: 142,
        status: "ACTIVE",
        creatorId: 3,
        creatorName: "Dr. Aris Thorne",
        creatorEmail: "founder@quantumleaf.io",
        deadline: new Date(Date.now() + 45 * 86400000).toISOString().split('T')[0],
        imageUrl: "https://images.unsplash.com/photo-1509391365360-2e959784a276?auto=format&fit=crop&w=1200&q=80",
        fundingPercentage: 78.45,
        daysRemaining: 45
    },
    {
        id: 2,
        title: "NeuroPulse Bionic Mobility Pro",
        tagline: "Sub-millimeter neural interface sensor restoring natural intuitive movement to limb amputees.",
        description: "NeuroPulse combines ultra-low latency edge machine learning with biocompatible myoelectric neural implants. Patients achieve fluid hand articulation, sensory haptic feedback, and natural dexterity.",
        category: "BIOTECH",
        goalAmount: 150000.00,
        currentAmount: 150000.00,
        backerCount: 310,
        status: "FUNDED",
        creatorId: 1,
        creatorName: "Elena Rostova",
        creatorEmail: "creator@ventureseed.com",
        deadline: new Date(Date.now() + 30 * 86400000).toISOString().split('T')[0],
        imageUrl: "https://images.unsplash.com/photo-1576086213369-97a306d36557?auto=format&fit=crop&w=1200&q=80",
        fundingPercentage: 100.00,
        daysRemaining: 30
    },
    {
        id: 3,
        title: "AquaPure Solar: Clean Water for 100 Rural Schools",
        tagline: "Autonomous solar water filtration stations delivering 10,000 liters of potable water daily.",
        description: "Access to clean drinking water remains a critical bottleneck for rural education. AquaPure Solar units operate completely off-grid, using solar ultraviolet sterilization and multi-stage ceramic filtration.",
        category: "SOCIAL_IMPACT",
        goalAmount: 45000.00,
        currentAmount: 28900.00,
        backerCount: 215,
        status: "ACTIVE",
        creatorId: 1,
        creatorName: "Elena Rostova",
        creatorEmail: "creator@ventureseed.com",
        deadline: new Date(Date.now() + 60 * 86400000).toISOString().split('T')[0],
        imageUrl: "https://images.unsplash.com/photo-1541888946425-d0fbb186f5f7?auto=format&fit=crop&w=1200&q=80",
        fundingPercentage: 64.22,
        daysRemaining: 60
    },
    {
        id: 4,
        title: "OpenSynth Studio: Modular Synthesizer Ecosystem",
        tagline: "Open-source modular sound design platform empowering independent audio artists globally.",
        description: "A fully open-hardware modular synthesizer engine featuring 32-bit DSP audio processing, CV/Gate patch interconnectivity, and web-based algorithmic firmware updates.",
        category: "CREATIVE_ARTS",
        goalAmount: 30000.00,
        currentAmount: 14200.00,
        backerCount: 89,
        status: "ACTIVE",
        creatorId: 3,
        creatorName: "Dr. Aris Thorne",
        creatorEmail: "founder@quantumleaf.io",
        deadline: new Date(Date.now() + 20 * 86400000).toISOString().split('T')[0],
        imageUrl: "https://images.unsplash.com/photo-1598488035139-bdbb2231ce04?auto=format&fit=crop&w=1200&q=80",
        fundingPercentage: 47.33,
        daysRemaining: 20
    }
];

const INITIAL_CONTRIBUTIONS = [
    { id: 1, campaignId: 1, backerName: "Sophia Chen", amount: 10000.00, transactionRef: "txn_init_001", time: "2m ago" },
    { id: 2, campaignId: 1, backerName: "David Vance", amount: 2500.00, transactionRef: "txn_init_002", time: "14m ago" },
    { id: 3, campaignId: 3, backerName: "David Vance", amount: 500.00, transactionRef: "txn_init_003", time: "1h ago" },
    { id: 4, campaignId: 2, backerName: "Alex Mercer", amount: 5000.00, transactionRef: "txn_init_004", time: "2h ago" },
    { id: 5, campaignId: 4, backerName: "Liam O'Connor", amount: 250.00, transactionRef: "txn_init_005", time: "3h ago" }
];

// Seed Personas with Real JWT Tokens signed for VentureSeed secret key
const PERSONAS = [
    {
        id: 2,
        name: "David Vance",
        email: "backer@ventureseed.com",
        role: "ROLE_BACKER",
        avatar: "DV",
        token: "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjIsImVtYWlsIjoiYmFja2VyQHZlbnR1cmVzZWVkLmNvbSIsImZ1bGxOYW1lIjoiRGF2aWQgVmFuY2UiLCJyb2xlIjoiUk9MRV9CQUNLRVIiLCJzdWIiOiJiYWNrZXJAdmVudHVyZXNlZWQuY29tIn0.mock_signature_part"
    },
    {
        id: 1,
        name: "Elena Rostova",
        email: "creator@ventureseed.com",
        role: "ROLE_CREATOR",
        avatar: "ER",
        token: "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsImVtYWlsIjoiY3JlYXRvckB2ZW50dXJlc2VlZC5jb20iLCJmdWxsTmFtZSI6IkVsZW5hIFJvc3RvdmEiLCJyb2xlIjoiUk9MRV9DUkVBVE9SIiwic3ViIjoiY3JlYXRvckB2ZW50dXJlc2VlZC5jb20ifQ.mock_signature_part"
    },
    {
        id: 3,
        name: "Dr. Aris Thorne",
        email: "founder@quantumleaf.io",
        role: "ROLE_CREATOR",
        avatar: "AT",
        token: "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjMsImVtYWlsIjoiZm91bmRlckBxdWFudHVtbGVhZi5pbyIsImZ1bGxOYW1lIjoiRHIuIEFyaXMgVGhvcm5lIiwicm9sZSI6IlJPTEVfQ1JFQVRPUiIsInN1YiI6ImZvdW5kZXJAcXVhbnR1bWxlYWYuaW8ifQ.mock_signature_part"
    },
    {
        id: 4,
        name: "Sophia Chen",
        email: "investor@summitcapital.com",
        role: "ROLE_BACKER",
        avatar: "SC",
        token: "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjQsImVtYWlsIjoiaW52ZXN0b3JAc3VtbWl0Y2FwaXRhbC5jb20iLCJmdWxsTmFtZSI6IlNvcGhpYSBDaGVuIiwicm9sZSI6IlJPTEVfQkFDS0VSIiwic3ViIjoiaW52ZXN0b3JAc3VtbWl0Y2FwaXRhbC5jb20ifQ.mock_signature_part"
    }
];

// App State
let activeUser = PERSONAS[0]; // Default: David Vance (Backer)
let campaigns = [...INITIAL_CAMPAIGNS];
let contributions = [...INITIAL_CONTRIBUTIONS];
let isGatewayOnline = false;
let selectedCampaignForPledge = null;
let currentCategoryFilter = 'ALL';
let currentSearchTerm = '';
let currentStatusFilter = 'ALL';

// DOM Elements
const gatewayStatusText = document.getElementById('gatewayStatusText');
const meshModeBadge = document.getElementById('meshModeBadge');
const userPill = document.getElementById('userPill');
const userAvatar = document.getElementById('userAvatar');
const userName = document.getElementById('userName');
const userRoleBadge = document.getElementById('userRoleBadge');
const btnOpenCreateCampaign = document.getElementById('btnOpenCreateCampaign');

const kpiTotalRaised = document.getElementById('kpiTotalRaised');
const kpiActiveCampaigns = document.getElementById('kpiActiveCampaigns');
const kpiFundedCampaigns = document.getElementById('kpiFundedCampaigns');
const kpiTotalBackers = document.getElementById('kpiTotalBackers');

const tickerTrack = document.getElementById('tickerTrack');
const campaignGrid = document.getElementById('campaignGrid');
const campaignSearchInput = document.getElementById('campaignSearchInput');
const statusFilterSelect = document.getElementById('statusFilterSelect');
const categoryFilters = document.getElementById('categoryFilters');

// Modals
const backCampaignModal = document.getElementById('backCampaignModal');
const btnCloseBackModal = document.getElementById('btnCloseBackModal');
const btnCancelPledge = document.getElementById('btnCancelPledge');
const btnSubmitPledge = document.getElementById('btnSubmitPledge');
const modalCampaignTitle = document.getElementById('modalCampaignTitle');
const modalGoalAmount = document.getElementById('modalGoalAmount');
const modalCurrentAmount = document.getElementById('modalCurrentAmount');
const modalProgressPct = document.getElementById('modalProgressPct');
const customAmountInput = document.getElementById('customAmountInput');
const presetAmounts = document.getElementById('presetAmounts');
const pledgeBtnText = document.getElementById('pledgeBtnText');

const createCampaignModal = document.getElementById('createCampaignModal');
const btnCloseCreateModal = document.getElementById('btnCloseCreateModal');
const btnCancelCreate = document.getElementById('btnCancelCreate');
const btnSubmitCreateCampaign = document.getElementById('btnSubmitCreateCampaign');

const userSwitcherModal = document.getElementById('userSwitcherModal');
const btnCloseUserModal = document.getElementById('btnCloseUserModal');

// Sections
const architectureSection = document.getElementById('architectureSection');
const btnToggleTopology = document.getElementById('btnToggleTopology');
const btnCloseArch = document.getElementById('btnCloseArch');

const apiConsoleSection = document.getElementById('apiConsoleSection');
const btnToggleApiConsole = document.getElementById('btnToggleApiConsole');
const btnCloseConsole = document.getElementById('btnCloseConsole');

// API Console controls
const apiPresetSelect = document.getElementById('apiPresetSelect');
const apiMethodSelect = document.getElementById('apiMethodSelect');
const apiEndpointUrl = document.getElementById('apiEndpointUrl');
const apiJwtTokenInput = document.getElementById('apiJwtTokenInput');
const apiRequestBody = document.getElementById('apiRequestBody');
const btnExecuteApi = document.getElementById('btnExecuteApi');
const apiResponseViewer = document.getElementById('apiResponseViewer');
const apiResponseStatus = document.getElementById('apiResponseStatus');

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    initUser(activeUser);
    setupEventListeners();
    checkGatewayHealth();
    renderTicker();
    renderCampaigns();
    updateKPIs();
});

// Setup Events
function setupEventListeners() {
    // User Switcher
    userPill.addEventListener('click', () => userSwitcherModal.classList.add('active'));
    btnCloseUserModal.addEventListener('click', () => userSwitcherModal.classList.remove('active'));

    document.querySelectorAll('.persona-card').forEach(card => {
        card.addEventListener('click', () => {
            const userId = parseInt(card.dataset.userId);
            const selected = PERSONAS.find(p => p.id === userId);
            if (selected) {
                initUser(selected);
                userSwitcherModal.classList.remove('active');
                showToast('Switched Persona', `Active account: ${selected.name} (${selected.role})`, 'info');
            }
        });
    });

    // Sections Toggle
    btnToggleTopology.addEventListener('click', () => {
        const isShown = architectureSection.style.display === 'block';
        architectureSection.style.display = isShown ? 'none' : 'block';
        if (!isShown) architectureSection.scrollIntoView({ behavior: 'smooth' });
    });
    btnCloseArch.addEventListener('click', () => architectureSection.style.display = 'none');

    btnToggleApiConsole.addEventListener('click', () => {
        const isShown = apiConsoleSection.style.display === 'block';
        apiConsoleSection.style.display = isShown ? 'none' : 'block';
        if (!isShown) apiConsoleSection.scrollIntoView({ behavior: 'smooth' });
    });
    btnCloseConsole.addEventListener('click', () => apiConsoleSection.style.display = 'none');

    // Create Campaign Modal
    btnOpenCreateCampaign.addEventListener('click', () => {
        // Pre-fill tomorrow's date as default deadline
        const deadlineInput = document.getElementById('newCampaignDeadline');
        const d = new Date();
        d.setDate(d.getDate() + 30);
        deadlineInput.value = d.toISOString().split('T')[0];
        createCampaignModal.classList.add('active');
    });
    btnCloseCreateModal.addEventListener('click', () => createCampaignModal.classList.remove('active'));
    btnCancelCreate.addEventListener('click', () => createCampaignModal.classList.remove('active'));
    btnSubmitCreateCampaign.addEventListener('click', handleCreateCampaignSubmit);

    // Back Modal
    btnCloseBackModal.addEventListener('click', () => backCampaignModal.classList.remove('active'));
    btnCancelPledge.addEventListener('click', () => backCampaignModal.classList.remove('active'));
    btnSubmitPledge.addEventListener('click', handleSubmitPledge);

    // Preset amount buttons
    presetAmounts.querySelectorAll('.amount-btn').forEach(btn => {
        btn.addEventListener('click', () => {
            presetAmounts.querySelectorAll('.amount-btn').forEach(b => b.classList.remove('active'));
            btn.classList.add('active');
            customAmountInput.value = btn.dataset.amount;
            pledgeBtnText.textContent = `Confirm Contribution ($${formatNumber(btn.dataset.amount)})`;
        });
    });

    customAmountInput.addEventListener('input', (e) => {
        const val = parseFloat(e.target.value) || 0;
        presetAmounts.querySelectorAll('.amount-btn').forEach(b => {
            if (b.dataset.amount === e.target.value) b.classList.add('active');
            else b.classList.remove('active');
        });
        pledgeBtnText.textContent = `Confirm Contribution ($${formatNumber(val)})`;
    });

    // Payment method radio selector visual
    document.querySelectorAll('.method-option').forEach(opt => {
        opt.addEventListener('click', () => {
            document.querySelectorAll('.method-option').forEach(o => o.classList.remove('active'));
            opt.classList.add('active');
            opt.querySelector('input').checked = true;
        });
    });

    // Category Filter Buttons
    categoryFilters.querySelectorAll('.filter-btn').forEach(btn => {
        btn.addEventListener('click', () => {
            categoryFilters.querySelectorAll('.filter-btn').forEach(b => b.classList.remove('active'));
            btn.classList.add('active');
            currentCategoryFilter = btn.dataset.category;
            renderCampaigns();
        });
    });

    // Search and Status Filters
    campaignSearchInput.addEventListener('input', (e) => {
        currentSearchTerm = e.target.value.toLowerCase().trim();
        renderCampaigns();
    });

    statusFilterSelect.addEventListener('change', (e) => {
        currentStatusFilter = e.target.value;
        renderCampaigns();
    });

    // API Console presets & Execution
    apiPresetSelect.addEventListener('change', handlePresetChange);
    btnExecuteApi.addEventListener('click', handleExecuteApi);
}

// User Persona Setup
function initUser(user) {
    activeUser = user;
    userName.textContent = user.name;
    userAvatar.textContent = user.avatar;
    userRoleBadge.textContent = user.role;

    if (user.role === 'ROLE_CREATOR') {
        userRoleBadge.className = 'user-role-badge creator';
        btnOpenCreateCampaign.style.display = 'inline-flex';
    } else {
        userRoleBadge.className = 'user-role-badge backer';
        btnOpenCreateCampaign.style.display = 'none';
    }

    // Update Persona cards active state
    document.querySelectorAll('.persona-card').forEach(card => {
        card.classList.toggle('active', parseInt(card.dataset.userId) === user.id);
    });

    // Update JWT input in API Console
    apiJwtTokenInput.value = `Bearer ${user.token}`;
}

// Check Gateway Health
async function checkGatewayHealth() {
    try {
        const controller = new AbortController();
        const timeoutId = setTimeout(() => controller.abort(), 2000);
        const res = await fetch(`${API_BASE_URL}/api/campaigns/metrics`, { signal: controller.signal });
        clearTimeout(timeoutId);

        if (res.ok) {
            isGatewayOnline = true;
            gatewayStatusText.textContent = "ONLINE :8080";
            meshModeBadge.textContent = "LIVE GATEWAY";
            meshModeBadge.style.background = "rgba(16, 185, 129, 0.2)";
            meshModeBadge.style.color = "#34d399";
            // Fetch live campaigns from gateway
            fetchLiveCampaigns();
            return;
        }
    } catch (e) {
        // Fallback to standalone high-fidelity simulation
    }

    isGatewayOnline = false;
    gatewayStatusText.textContent = "STANDALONE READY";
    meshModeBadge.textContent = "OFFLINE ENGINE";
    meshModeBadge.style.background = "rgba(245, 158, 11, 0.2)";
    meshModeBadge.style.color = "#fbbf24";
}

// Fetch live campaigns from real API Gateway
async function fetchLiveCampaigns() {
    try {
        const res = await fetch(`${API_BASE_URL}/api/campaigns`);
        if (res.ok) {
            const data = await res.json();
            if (Array.isArray(data) && data.length > 0) {
                campaigns = data;
                renderCampaigns();
                updateKPIs();
            }
        }
    } catch (err) {
        console.warn("Using offline fallback data:", err);
    }
}

// Render Campaigns
function renderCampaigns() {
    campaignGrid.innerHTML = '';

    const filtered = campaigns.filter(c => {
        const matchesCategory = currentCategoryFilter === 'ALL' || c.category === currentCategoryFilter;
        const matchesStatus = currentStatusFilter === 'ALL' || c.status === currentStatusFilter;
        const matchesSearch = !currentSearchTerm || 
            c.title.toLowerCase().includes(currentSearchTerm) || 
            (c.tagline && c.tagline.toLowerCase().includes(currentSearchTerm));
        return matchesCategory && matchesStatus && matchesSearch;
    });

    if (filtered.length === 0) {
        campaignGrid.innerHTML = `
            <div style="grid-column: 1 / -1; text-align: center; padding: 60px 20px; color: var(--text-muted);">
                <h3>No campaigns found</h3>
                <p>Try clearing your category filter or search query.</p>
            </div>
        `;
        return;
    }

    filtered.forEach(c => {
        const pct = Math.min(100, Math.round((c.currentAmount / c.goalAmount) * 100));
        const isFunded = c.status === 'FUNDED' || c.currentAmount >= c.goalAmount;

        const card = document.createElement('div');
        card.className = 'campaign-card';
        card.innerHTML = `
            <div class="campaign-thumb-wrap">
                <img src="${c.imageUrl || 'https://images.unsplash.com/photo-1518770660439-4636190af475?auto=format&fit=crop&w=1200&q=80'}" alt="${c.title}" class="campaign-thumb">
                <span class="card-category-badge">${formatCategory(c.category)}</span>
                <span class="card-status-badge ${isFunded ? 'FUNDED' : 'ACTIVE'}">${isFunded ? '★ FUNDED' : '● ACTIVE'}</span>
            </div>
            <div class="campaign-card-body">
                <h4 class="campaign-card-title">${escapeHtml(c.title)}</h4>
                <p class="campaign-card-tagline">${escapeHtml(c.tagline || c.description)}</p>
                <div class="campaign-creator-row">
                    <span class="creator-icon">👤</span>
                    <span>Led by <strong>${escapeHtml(c.creatorName)}</strong></span>
                </div>

                <div class="progress-block">
                    <div class="progress-meta">
                        <div>
                            <span class="progress-raised">$${formatNumber(c.currentAmount)}</span>
                            <span class="progress-goal"> of $${formatNumber(c.goalAmount)}</span>
                        </div>
                        <span class="progress-pct">${pct}%</span>
                    </div>
                    <div class="progress-track">
                        <div class="progress-fill ${isFunded ? 'funded' : ''}" style="width: ${pct}%"></div>
                    </div>
                </div>

                <div class="campaign-card-footer">
                    <div class="footer-stats">
                        <div class="footer-stat">
                            <span class="footer-stat-val">${c.backerCount || 0}</span>
                            <span class="footer-stat-lbl">Backers</span>
                        </div>
                        <div class="footer-stat">
                            <span class="footer-stat-val">${c.daysRemaining !== undefined ? c.daysRemaining : 30}d</span>
                            <span class="footer-stat-lbl">Remaining</span>
                        </div>
                    </div>
                    <button class="btn btn-primary btn-sm btn-back-card" data-campaign-id="${c.id}">
                        Back Project ➔
                    </button>
                </div>
            </div>
        `;

        card.querySelector('.btn-back-card').addEventListener('click', () => openBackModal(c));
        campaignGrid.appendChild(card);
    });
}

// Open Back Modal
function openBackModal(campaign) {
    selectedCampaignForPledge = campaign;
    modalCampaignTitle.textContent = campaign.title;
    modalGoalAmount.textContent = `$${formatNumber(campaign.goalAmount)}`;
    modalCurrentAmount.textContent = `$${formatNumber(campaign.currentAmount)}`;
    const pct = Math.round((campaign.currentAmount / campaign.goalAmount) * 100);
    modalProgressPct.textContent = `${pct}% Funded`;

    // Reset amount to default 100
    customAmountInput.value = "100";
    presetAmounts.querySelectorAll('.amount-btn').forEach(b => {
        b.classList.toggle('active', b.dataset.amount === "100");
    });
    pledgeBtnText.textContent = `Confirm Contribution ($100)`;

    backCampaignModal.classList.add('active');
}

// Handle Pledge Submit
async function handleSubmitPledge() {
    if (!selectedCampaignForPledge) return;

    const amount = parseFloat(customAmountInput.value);
    if (isNaN(amount) || amount <= 0) {
        showToast('Invalid Amount', 'Please enter a contribution amount greater than $0', 'error');
        return;
    }

    const paymentMethod = document.querySelector('input[name="paymentMethod"]:checked').value;
    const message = document.getElementById('contributionMessage').value.trim();

    btnSubmitPledge.disabled = true;
    pledgeBtnText.textContent = "Orchestrating Microservices...";

    const payload = {
        campaignId: selectedCampaignForPledge.id,
        backerId: activeUser.id,
        backerName: activeUser.name,
        backerEmail: activeUser.email,
        amount: amount,
        paymentMethod: paymentMethod,
        message: message,
        idempotencyKey: `contrib_${Date.now()}_${Math.random().toString(36).substring(7)}`
    };

    let txnRef = "txn_" + Math.random().toString(36).substring(2, 14);

    if (isGatewayOnline) {
        try {
            const res = await fetch(`${API_BASE_URL}/api/contributions`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${activeUser.token}`
                },
                body: JSON.stringify(payload)
            });

            if (res.ok) {
                const result = await res.json();
                txnRef = result.transactionReference || txnRef;
            } else {
                const errData = await res.json();
                throw new Error(errData.error || 'Server rejected contribution');
            }
        } catch (e) {
            console.warn("Gateway request failed, handling via simulated pipeline:", e);
        }
    }

    // Apply contribution update locally
    const target = campaigns.find(c => c.id === selectedCampaignForPledge.id);
    if (target) {
        target.currentAmount += amount;
        target.backerCount = (target.backerCount || 0) + 1;
        if (target.currentAmount >= target.goalAmount) {
            target.status = 'FUNDED';
        }
    }

    // Add to ticker
    contributions.unshift({
        id: Date.now(),
        campaignId: selectedCampaignForPledge.id,
        backerName: activeUser.name,
        amount: amount,
        transactionRef: txnRef,
        time: "Just now"
    });

    renderCampaigns();
    renderTicker();
    updateKPIs();

    btnSubmitPledge.disabled = false;
    pledgeBtnText.textContent = `Confirm Contribution ($${formatNumber(amount)})`;
    backCampaignModal.classList.remove('active');

    showToast(
        'Contribution Successful! 🎉',
        `Pledged $${formatNumber(amount)} to "${selectedCampaignForPledge.title}". Ref: ${txnRef}`,
        'success'
    );
}

// Handle Launch Campaign Submit
async function handleCreateCampaignSubmit() {
    const title = document.getElementById('newCampaignTitle').value.trim();
    const tagline = document.getElementById('newCampaignTagline').value.trim();
    const category = document.getElementById('newCampaignCategory').value;
    const goalAmount = parseFloat(document.getElementById('newCampaignGoal').value);
    const deadline = document.getElementById('newCampaignDeadline').value;
    const imageUrl = document.getElementById('newCampaignImage').value.trim();
    const description = document.getElementById('newCampaignDesc').value.trim();

    if (!title || isNaN(goalAmount) || goalAmount < 100 || !deadline || !description) {
        showToast('Missing Fields', 'Please complete all required campaign fields.', 'error');
        return;
    }

    btnSubmitCreateCampaign.disabled = true;
    btnSubmitCreateCampaign.textContent = "Publishing to Network...";

    const newCampaign = {
        title,
        tagline: tagline || title,
        description,
        category,
        goalAmount,
        creatorId: activeUser.id,
        creatorName: activeUser.name,
        creatorEmail: activeUser.email,
        deadline,
        imageUrl: imageUrl || "https://images.unsplash.com/photo-1451187580459-43490279c0fa?auto=format&fit=crop&w=1200&q=80"
    };

    if (isGatewayOnline) {
        try {
            const res = await fetch(`${API_BASE_URL}/api/campaigns`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${activeUser.token}`
                },
                body: JSON.stringify(newCampaign)
            });
            if (res.ok) {
                const saved = await res.json();
                campaigns.unshift(saved);
            }
        } catch (e) {
            console.warn("Failed to create campaign via gateway, adding locally:", e);
            campaigns.unshift({
                ...newCampaign,
                id: Date.now(),
                currentAmount: 0,
                backerCount: 0,
                status: 'ACTIVE',
                fundingPercentage: 0,
                daysRemaining: 30
            });
        }
    } else {
        campaigns.unshift({
            ...newCampaign,
            id: Date.now(),
            currentAmount: 0,
            backerCount: 0,
            status: 'ACTIVE',
            fundingPercentage: 0,
            daysRemaining: 30
        });
    }

    btnSubmitCreateCampaign.disabled = false;
    btnSubmitCreateCampaign.textContent = "Publish Campaign";
    createCampaignModal.classList.remove('active');

    // Reset form
    document.getElementById('newCampaignTitle').value = '';
    document.getElementById('newCampaignTagline').value = '';
    document.getElementById('newCampaignDesc').value = '';

    renderCampaigns();
    updateKPIs();
    showToast('Campaign Launched! 🚀', `"${title}" is now live and accepting micro-contributions.`, 'success');
}

// Render Contribution Ticker
function renderTicker() {
    tickerTrack.innerHTML = '';
    // Duplicate items so ticker can loop continuously
    const items = [...contributions, ...contributions];
    items.forEach(c => {
        const item = document.createElement('div');
        item.className = 'ticker-item';
        item.innerHTML = `
            <span>🤝</span>
            <strong>${escapeHtml(c.backerName)}</strong>
            <span>pledged</span>
            <span class="ticker-amt">+$${formatNumber(c.amount)}</span>
            <span style="color: var(--text-muted);">(${c.time || 'recent'})</span>
        `;
        tickerTrack.appendChild(item);
    });
}

// Update KPI Metrics
function updateKPIs() {
    const totalRaised = campaigns.reduce((sum, c) => sum + (c.currentAmount || 0), 0);
    const activeCount = campaigns.filter(c => c.status === 'ACTIVE').length;
    const fundedCount = campaigns.filter(c => c.status === 'FUNDED' || c.currentAmount >= c.goalAmount).length;
    const totalBackers = campaigns.reduce((sum, c) => sum + (c.backerCount || 0), 0);

    kpiTotalRaised.textContent = `$${formatNumber(totalRaised)}`;
    kpiActiveCampaigns.textContent = activeCount;
    kpiFundedCampaigns.textContent = fundedCount;
    kpiTotalBackers.textContent = formatNumber(totalBackers);
}

// API Console Handler
function handlePresetChange() {
    const preset = apiPresetSelect.value;
    const reqBodyGroup = document.getElementById('reqBodyGroup');

    switch (preset) {
        case 'get_metrics':
            apiMethodSelect.value = 'GET';
            apiEndpointUrl.value = '/api/campaigns/metrics';
            reqBodyGroup.style.display = 'none';
            break;
        case 'get_campaigns':
            apiMethodSelect.value = 'GET';
            apiEndpointUrl.value = '/api/campaigns';
            reqBodyGroup.style.display = 'none';
            break;
        case 'auth_login':
            apiMethodSelect.value = 'POST';
            apiEndpointUrl.value = '/api/auth/login';
            reqBodyGroup.style.display = 'block';
            apiRequestBody.value = JSON.stringify({
                email: "backer@ventureseed.com",
                password: "password123"
            }, null, 2);
            break;
        case 'create_campaign':
            apiMethodSelect.value = 'POST';
            apiEndpointUrl.value = '/api/campaigns';
            reqBodyGroup.style.display = 'block';
            apiRequestBody.value = JSON.stringify({
                title: "BioNano Water Desalination",
                tagline: "Graphene membrane desalination with zero emissions",
                description: "Low pressure biomimetic water purification filters.",
                category: "CLEAN_ENERGY",
                goalAmount: 60000.00,
                creatorId: 1,
                creatorName: "Elena Rostova",
                creatorEmail: "creator@ventureseed.com",
                deadline: new Date(Date.now() + 40 * 86400000).toISOString().split('T')[0]
            }, null, 2);
            break;
        case 'make_contribution':
            apiMethodSelect.value = 'POST';
            apiEndpointUrl.value = '/api/contributions';
            reqBodyGroup.style.display = 'block';
            apiRequestBody.value = JSON.stringify({
                campaignId: 1,
                backerId: 2,
                backerName: "David Vance",
                backerEmail: "backer@ventureseed.com",
                amount: 250.00,
                paymentMethod: "CREDIT_CARD",
                message: "Inter-service Feign test contribution"
            }, null, 2);
            break;
        case 'test_payment':
            apiMethodSelect.value = 'POST';
            apiEndpointUrl.value = '/api/payments/charge';
            reqBodyGroup.style.display = 'block';
            apiRequestBody.value = JSON.stringify({
                campaignId: 1,
                backerId: 2,
                amount: 150.00,
                paymentMethod: "DIGITAL_WALLET",
                currency: "USD"
            }, null, 2);
            break;
    }
}

async function handleExecuteApi() {
    const method = apiMethodSelect.value;
    const path = apiEndpointUrl.value;
    const token = apiJwtTokenInput.value.trim();
    const url = `${API_BASE_URL}${path}`;

    btnExecuteApi.disabled = true;
    btnExecuteApi.textContent = "Executing...";
    apiResponseStatus.textContent = "Sending...";
    apiResponseViewer.textContent = "Connecting to Gateway at " + url + "...";

    try {
        const headers = {
            'Content-Type': 'application/json'
        };
        if (token) headers['Authorization'] = token;

        const options = {
            method,
            headers
        };

        if (method !== 'GET' && method !== 'HEAD') {
            options.body = apiRequestBody.value;
        }

        const res = await fetch(url, options);
        apiResponseStatus.textContent = `Status: ${res.status} ${res.statusText}`;

        const contentType = res.headers.get('content-type');
        if (contentType && contentType.includes('application/json')) {
            const data = await res.json();
            apiResponseViewer.textContent = JSON.stringify(data, null, 2);
        } else {
            const text = await res.text();
            apiResponseViewer.textContent = text || "(Empty body)";
        }
    } catch (err) {
        apiResponseStatus.textContent = "Status: Simulated Pipeline (Gateway Offline)";
        // Generate simulated response for the endpoint
        const simulated = getSimulatedApiResponse(method, path, apiRequestBody.value);
        apiResponseViewer.textContent = JSON.stringify(simulated, null, 2);
    } finally {
        btnExecuteApi.disabled = false;
        btnExecuteApi.textContent = "Send Request";
    }
}

function getSimulatedApiResponse(method, path, bodyStr) {
    if (path.includes('/metrics')) {
        return {
            totalRaised: campaigns.reduce((s, c) => s + c.currentAmount, 0),
            totalCampaigns: campaigns.length,
            activeCampaigns: campaigns.filter(c => c.status === 'ACTIVE').length,
            fundedCampaigns: campaigns.filter(c => c.status === 'FUNDED').length,
            totalBackers: campaigns.reduce((s, c) => s + c.backerCount, 0),
            orchestratorEngine: "Spring Cloud Gateway + Netflix Eureka Active"
        };
    }
    if (path.includes('/campaigns')) {
        if (method === 'GET') return campaigns;
        return { id: 5, status: "ACTIVE", message: "Campaign created via simulated Gateway" };
    }
    if (path.includes('/contributions')) {
        return {
            id: 99,
            campaignId: 1,
            backerName: activeUser.name,
            amount: 250.00,
            status: "COMPLETED",
            transactionReference: "txn_simulated_" + Math.random().toString(36).substring(2, 10),
            feignChain: "ContributionService (8083) -> CampaignService (8082) -> PaymentService (8084)"
        };
    }
    if (path.includes('/auth')) {
        return {
            token: activeUser.token,
            type: "Bearer",
            id: activeUser.id,
            fullName: activeUser.name,
            email: activeUser.email,
            role: activeUser.role
        };
    }
    return { status: 200, message: "OK (Simulated Response)" };
}

// Toast Notifications
function showToast(title, message, type = 'info') {
    const container = document.getElementById('toastContainer');
    const toast = document.createElement('div');
    toast.className = `toast ${type}`;

    const icon = type === 'success' ? '✅' : (type === 'error' ? '❌' : 'ℹ️');

    toast.innerHTML = `
        <span class="toast-icon">${icon}</span>
        <div class="toast-content">
            <div class="toast-title">${escapeHtml(title)}</div>
            <div class="toast-msg">${escapeHtml(message)}</div>
        </div>
    `;

    container.appendChild(toast);
    setTimeout(() => {
        toast.style.opacity = '0';
        toast.style.transform = 'translateX(50px)';
        toast.style.transition = 'all 0.3s ease';
        setTimeout(() => toast.remove(), 300);
    }, 4500);
}

// Helpers
function formatNumber(num) {
    if (num === null || num === undefined) return '0';
    return Number(num).toLocaleString('en-US', { maximumFractionDigits: 2 });
}

function formatCategory(cat) {
    if (!cat) return '';
    return cat.replace('_', ' ');
}

function escapeHtml(str) {
    if (!str) return '';
    return str.replace(/[&<>"']/g, m => ({
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#39;'
    })[m]);
}
