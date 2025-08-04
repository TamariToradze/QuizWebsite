const planets = document.querySelectorAll(".planet");
let startTime = Date.now();

function updatePositions() {
  const now = Date.now() - startTime;

  planets.forEach((planet, index) => {
    const orbitTime = (index + 1) * 5000; // Adjust orbit speed
    const distanceFromSun = (index + 1) * 50;
    const diameter = getPlanetDiameter(index + 1);
    const angle = ((now % orbitTime) / orbitTime) * 360;
    const x = distanceFromSun * Math.cos((angle * Math.PI) / 180);
    const y = distanceFromSun * Math.sin((angle * Math.PI) / 180);

    planet.style.width = `${diameter}px`;
    planet.style.height = `${diameter}px`;
    planet.style.left = `calc(50% - ${diameter / 2}px + ${x}px)`;
    planet.style.top = `calc(50% - ${diameter / 2}px + ${y}px)`;
  });
}

function getPlanetDiameter(index) {
  const diameters = [6, 15, 16, 8, 160, 130, 60, 55, 4]; // Raw diameters
  const scaleFactor = [1, 0.7, 0.6, 0.4, 0.2, 0.1, 0.08, 0.05, 0.02]; // Adjust for visual scale
  return diameters[index - 1] * scaleFactor[index - 1];
}

function createStars() {
  const starField = document.querySelector(".star-field");
  const numStars = 100;

  for (let i = 0; i < numStars; i++) {
    const star = document.createElement("div");
    star.classList.add("star");
    star.style.left = `${Math.random() * 100}%`;
    star.style.top = `${Math.random() * 100}%`;
    star.style.animationDelay = `${Math.random()}s`;
    starField.appendChild(star);
  }
}

window.addEventListener('load', () => {
  createStars();
  setInterval(updatePositions, 1000 / 60); // 60 FPS
});
