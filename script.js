// ===== BASIC SETUP =====
const scene = new THREE.Scene();

// Camera
const camera = new THREE.PerspectiveCamera(
  75,
  window.innerWidth / window.innerHeight,
  0.1,
  1000
);

// Renderer (FIXED)
const renderer = new THREE.WebGLRenderer({
  alpha: true,
  antialias: true
});

renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);

// ===== LIGHTING =====
const light = new THREE.DirectionalLight(0xffffff, 1);
light.position.set(5, 5, 5);
scene.add(light);

const ambient = new THREE.AmbientLight(0x404040, 2);
scene.add(ambient);

// ===== 3D COMPUTER =====

// Screen
const screen = new THREE.Mesh(
  new THREE.BoxGeometry(3, 2, 0.2),
  new THREE.MeshStandardMaterial({ color: 0x111111 })
);
screen.position.y = 1;
scene.add(screen);

// Keyboard
const keyboard = new THREE.Mesh(
  new THREE.BoxGeometry(3, 0.2, 1.5),
  new THREE.MeshStandardMaterial({ color: 0x333333 })
);
keyboard.position.y = -1;
scene.add(keyboard);

// Base
const base = new THREE.Mesh(
  new THREE.BoxGeometry(0.5, 1, 0.5),
  new THREE.MeshStandardMaterial({ color: 0x222222 })
);
base.position.y = 0;
scene.add(base);

// Camera position
camera.position.z = 6;

// ===== SCROLL =====
let scrollY = 0;

window.addEventListener("scroll", () => {
  scrollY = window.scrollY;
});

// ===== ANIMATION LOOP =====
function animate() {
  requestAnimationFrame(animate);

  const t = Date.now() * 0.001;

  // Parallax
  screen.rotation.y = scrollY * 0.0005;
  keyboard.rotation.y = scrollY * 0.0005;

  camera.position.y = -scrollY * 0.002;

  // Floating animation
  screen.position.x = Math.sin(t) * 0.1;
  keyboard.position.x = Math.sin(t) * 0.1;

  renderer.render(scene, camera);
}

animate();

// ===== RESPONSIVE =====
window.addEventListener("resize", () => {
  camera.aspect = window.innerWidth / window.innerHeight;
  camera.updateProjectionMatrix();
  renderer.setSize(window.innerWidth, window.innerHeight);
});