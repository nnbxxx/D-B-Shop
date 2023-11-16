const container = document.getElementById("container");
const registerBtn = document.getElementById("register");
const loginBtn = document.getElementById("login");
const loginAdminBtn = document.getElementById("btnGoAdmin");

loginAdminBtn.addEventListener("click", () => {
  window.location.href = "/alogin";
});

registerBtn.addEventListener("click", () => {
  container.classList.add("active");
});

loginBtn.addEventListener("click", () => {
  container.classList.remove("active");
});

const handleGotoLoginAdminPage = () => {
  action = "/alogin";
  console.log(
    "🚀 ~ file: loginAdmin.js:15 ~ handleGotoLoginAdminPage ~ action:",
    action
  );
  window.location.href = action;
};
