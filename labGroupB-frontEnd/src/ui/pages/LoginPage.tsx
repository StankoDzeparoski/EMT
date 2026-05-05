import { useState } from 'react';
import {
  Box,
  Card,
  TextField,
  Button,
  Typography,
  Container,
  Alert,
  CircularProgress,
  Link as MuiLink
} from '@mui/material';
import { Link as RouterLink, useNavigate } from 'react-router-dom';
import useUser from '../../hooks/useUser';
import type { LoginUserRequestDto } from '../../api/types/User';

const LoginPage = () => {
  const navigate = useNavigate();
  const { login, loading, error } = useUser();
  const [formData, setFormData] = useState<LoginUserRequestDto>({
    username: '',
    password: ''
  });
  const [validationErrors, setValidationErrors] = useState<string[]>([]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const validateForm = () => {
    const errors: string[] = [];

    if (!formData.username.trim()) errors.push('Username is required');
    if (!formData.password) errors.push('Password is required');

    setValidationErrors(errors);
    return errors.length === 0;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!validateForm()) return;

    const result = await login(formData);
    if (result) {
      navigate('/');
    }
  };

  return (
    <Container maxWidth="sm">
      <Box sx={{ marginTop: 8, marginBottom: 8 }}>
        <Card sx={{ padding: 4 }}>
          <Typography variant="h4" component="h1" sx={{ marginBottom: 3, textAlign: 'center', fontWeight: 'bold' }}>
            Login
          </Typography>

          {error && <Alert severity="error" sx={{ marginBottom: 2 }}>{error.message}</Alert>}

          {validationErrors.length > 0 && (
            <Alert severity="warning" sx={{ marginBottom: 2 }}>
              <ul style={{ margin: 0, paddingLeft: 20 }}>
                {validationErrors.map((err, index) => (
                  <li key={index}>{err}</li>
                ))}
              </ul>
            </Alert>
          )}

          <Box component="form" onSubmit={handleSubmit} sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
            <TextField
              fullWidth
              label="Username"
              name="username"
              type="text"
              value={formData.username}
              onChange={handleChange}
              disabled={loading}
              autoFocus
            />

            <TextField
              fullWidth
              label="Password"
              name="password"
              type="password"
              value={formData.password}
              onChange={handleChange}
              disabled={loading}
            />

            <Button type="submit" variant="contained" size="large" disabled={loading} sx={{ marginTop: 2 }}>
              {loading ? <CircularProgress size={24} /> : 'Login'}
            </Button>
          </Box>

          <Typography sx={{ marginTop: 2, textAlign: 'center' }}>
            Don't have an account?{' '}
            <MuiLink component={RouterLink} to="/register" sx={{ cursor: 'pointer' }}>
              Register
            </MuiLink>
          </Typography>
        </Card>
      </Box>
    </Container>
  );
};

export default LoginPage;
