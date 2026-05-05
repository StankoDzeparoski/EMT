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
import type { RegisterUserRequestDto } from '../../api/types/User';

const RegisterPage = () => {
  const navigate = useNavigate();
  const { register, loading, error } = useUser();
  const [formData, setFormData] = useState<RegisterUserRequestDto>({
    name: '',
    surname: '',
    email: '',
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

    if (!formData.name.trim()) errors.push('Name is required');
    if (!formData.surname.trim()) errors.push('Surname is required');
    if (!formData.email.trim()) errors.push('Email is required');
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) {
      errors.push('Email is invalid');
    }
    if (!formData.username.trim()) errors.push('Username is required');
    if (formData.username.length < 3) errors.push('Username must be at least 3 characters');
    if (!formData.password) errors.push('Password is required');
    if (formData.password.length < 6) errors.push('Password must be at least 6 characters');

    setValidationErrors(errors);
    return errors.length === 0;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!validateForm()) return;

    const result = await register(formData);
    if (result) {
      navigate('/login');
    }
  };

  return (
    <Container maxWidth="sm">
      <Box sx={{ marginTop: 8, marginBottom: 8 }}>
        <Card sx={{ padding: 4 }}>
          <Typography variant="h4" component="h1" sx={{ marginBottom: 3, textAlign: 'center', fontWeight: 'bold' }}>
            Register
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
              label="Name"
              name="name"
              type="text"
              value={formData.name}
              onChange={handleChange}
              disabled={loading}
            />

            <TextField
              fullWidth
              label="Surname"
              name="surname"
              type="text"
              value={formData.surname}
              onChange={handleChange}
              disabled={loading}
            />

            <TextField
              fullWidth
              label="Email"
              name="email"
              type="email"
              value={formData.email}
              onChange={handleChange}
              disabled={loading}
            />

            <TextField
              fullWidth
              label="Username"
              name="username"
              type="text"
              value={formData.username}
              onChange={handleChange}
              disabled={loading}
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
              {loading ? <CircularProgress size={24} /> : 'Register'}
            </Button>
          </Box>

          <Typography sx={{ marginTop: 2, textAlign: 'center' }}>
            Already have an account?{' '}
            <MuiLink component={RouterLink} to="/login" sx={{ cursor: 'pointer' }}>
              Login
            </MuiLink>
          </Typography>
        </Card>
      </Box>
    </Container>
  );
};

export default RegisterPage;
