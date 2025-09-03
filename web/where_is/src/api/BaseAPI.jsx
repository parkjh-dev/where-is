import api from './axiosConfig';
import logoutAndRedirect from './axiosConfig';

const BASE_URL = import.meta.env.VITE_API_BASE_URL;

// Reads
export async function getDatas(path, query) {
  const queryString = new URLSearchParams(query).toString();
  const response = await api.get(`/${path}?${queryString}`);
  return response.data;
}

// Read
export async function getData(path, StringId) {
  const id = parseInt(StringId);
  const response = await api.get(`/${path}/${id}`);
  return response.data;
}

// Create
export async function addData(path, data) {
  const response = await api.post(`/${path}`, data);
  return response.data;
}

// Update
export async function updateData(path, id, data) {
  const response = await api.put(`/${path}/${id}`, data);
  return response.data;
}

// Delete
export async function deleteDatas(path, ids) {
  const idsParams = new URLSearchParams();

  if (Array.isArray(ids)) {
    ids.forEach(id => {
      idsParams.append("ids", id);
    });
  } else {
    idsParams.set("ids", ids);
  }
  
  const idsParamsString = idsParams.toString();
  console.log(idsParamsString);

  const response = await api.delete(`/${path}?${idsParamsString}`);
  return response.data;
}

export async function getToken(data) {
  try {
      const res = await fetch(`${BASE_URL}/auth/login`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          credentials: 'include',
          body: JSON.stringify(data),
      });
      
      if (!res.ok) {
          const errorData = await res.json().catch(() => ({}));
          throw new Error(`Login failed: ${res.status} ${res.statusText}`);
      }
      
      return res.json();
  } catch (error) {
      console.error('Network error details:', error);
      console.error('Error type:', error.name);
      console.error('Error message:', error.message);
      throw new Error(`Login failed: ${error.message}`);
  }
}

export async function logout() {
  try {
      const res = await fetch(`${BASE_URL}/auth/logout`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          credentials: 'include'
      });
      
      if (!res.ok) {
          throw new Error(`Logout failed: ${res.status} ${res.statusText}`);
      }
      
      return res.json();
  } catch (error) {
      console.error('Logout error:', error);
      throw new Error(`Logout failed: ${error.message}`);
  }
}
