import axiosInstance from '../axios/axios.ts';
import type {
  RegisterUserRequestDto,
  RegisterUserResponseDto,
  LoginUserRequestDto,
  LoginUserResponseDto
} from './types/User.ts';
import type { AuthLogDto } from './types/AuthLog.ts';

const userApi = {
  /**
   * Find user by username
   * GET /api/user/{username}
   */
  findByUsername: async (username: string) => {
    return await axiosInstance.get<RegisterUserResponseDto>(`/user/${username}`);
  },

  /**
   * Get current logged-in user
   * GET /api/user/me
   */
  me: async () => {
    return await axiosInstance.get<RegisterUserResponseDto>('/user/me');
  },

  /**
   * Register a new user
   * POST /api/user/register
   */
  register: async (registerDto: RegisterUserRequestDto) => {
    return await axiosInstance.post<RegisterUserResponseDto>('/user/register', registerDto);
  },

  /**
   * Login user and get JWT token
   * POST /api/user/login
   */
  login: async (loginDto: LoginUserRequestDto) => {
    return await axiosInstance.post<LoginUserResponseDto>('/user/login', loginDto);
  },

  /**
   * Get all authentication logs (Admin only)
   * GET /api/user/auth-logs
   */
  getAuthLogs: async () => {
    return await axiosInstance.get<AuthLogDto[]>('/user/auth-logs');
  }
};

export default userApi;
