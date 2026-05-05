import { useCallback, useState } from 'react';
import userApi from '../api/userApi';
import axiosInstance from '../axios/axios';
import type {
  RegisterUserRequestDto,
  RegisterUserResponseDto,
  LoginUserRequestDto,
  LoginUserResponseDto
} from '../api/types/User';

interface UseUserReturn {
  user: RegisterUserResponseDto | null;
  loading: boolean;
  error: Error | null;
  register: (data: RegisterUserRequestDto) => Promise<RegisterUserResponseDto | null>;
  login: (data: LoginUserRequestDto) => Promise<LoginUserResponseDto | null>;
  logout: () => void;
}

const useUser = (): UseUserReturn => {
  const [user, setUser] = useState<RegisterUserResponseDto | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<Error | null>(null);

  const register = useCallback(async (data: RegisterUserRequestDto) => {
    setLoading(true);
    try {
      const response = await userApi.register(data);
      setUser(response.data);
      setError(null);
      return response.data;
    } catch (err) {
      const error = err instanceof Error ? err : new Error('An unknown error occurred.');
      setError(error);
      return null;
    } finally {
      setLoading(false);
    }
  }, []);

  const login = useCallback(async (data: LoginUserRequestDto) => {
    setLoading(true);
    try {
      const response = await userApi.login(data);
      const token = response.data.token;

      // Store token in localStorage
      localStorage.setItem('authToken', token);

      // Set authorization header for future requests
      axiosInstance.defaults.headers.common['Authorization'] = `Bearer ${token}`;

      setError(null);
      return response.data;
    } catch (err) {
      const error = err instanceof Error ? err : new Error('An unknown error occurred.');
      setError(error);
      return null;
    } finally {
      setLoading(false);
    }
  }, []);

  const logout = useCallback(() => {
    setUser(null);
    localStorage.removeItem('authToken');
    delete axiosInstance.defaults.headers.common['Authorization'];
    setError(null);
  }, []);

  return { user, loading, error, register, login, logout };
};

export default useUser;
