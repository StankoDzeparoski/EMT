import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';

const Header = () => {
  return (
    <AppBar position="static" sx={{ marginBottom: 3 }}>
      <Toolbar>
        <Typography variant="h6" sx={{ flexGrow: 1, fontWeight: 'bold' }}>
          Travel Accommodations
        </Typography>
        <Box sx={{ display: 'flex', gap: 2 }}>
          <Button color="inherit" component={RouterLink} to="/">
            Home
          </Button>
          <Button color="inherit" component={RouterLink} to="/accommodations">
            Accommodations
          </Button>
          <Button color="inherit" component={RouterLink} to="/hosts">
            Hosts
          </Button>
          <Button color="inherit" component={RouterLink} to="/countries">
            Countries
          </Button>
          <Button color="inherit" component={RouterLink} to="/logs">
            Logs
          </Button>
          <Box sx={{ display: 'flex', gap: 1, marginLeft: 2, borderLeft: '1px solid rgba(255,255,255,0.3)', paddingLeft: 2 }}>
            <Button color="inherit" component={RouterLink} to="/login" variant="outlined">
              Login
            </Button>
            <Button color="inherit" component={RouterLink} to="/register" variant="contained">
              Register
            </Button>
          </Box>
        </Box>
      </Toolbar>
    </AppBar>
  );
};

export default Header;
