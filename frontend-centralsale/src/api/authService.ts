import type { LoginRequest, LoginResponse, SignupRequest, SignupResponse } from '../types/auth';
import axiosInstance from '../util/axios';

export const authService = {
  login: async (email: string, password: string): Promise<LoginResponse> => {
    const response = await axiosInstance.post<LoginResponse>('/users/login', {
      email,
      password,
    } as LoginRequest);
    return response.data;
  },

  signup: async (email: string, password: string): Promise<SignupResponse> => {
    const response = await axiosInstance.post<SignupResponse>('/users', {
      email,
      password,
    } as SignupRequest);
    return response.data;
  },

  logout: async (): Promise<void> => {
    try {
      await axiosInstance.post('/users/logout');
    } catch (error) {
      console.error('Logout error:', error);
    }
  },
};