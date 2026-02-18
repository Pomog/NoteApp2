# NoteApp2 — GitHub SSH push setup (what was done)

## 1) Prepared local Git repository
- Opened terminal in project folder: `~/AndroidStudioProjects/NoteApp2`
- Initialized Git repository: `git init`
- Added Android `.gitignore` (to avoid committing build/IDE files)
- Staged files: `git add .`
- Configured commit identity (name/email) with `git config`
- Created first commit: `git commit -m "Initial commit: NoteApp2"`
- Set branch to `main`: `git branch -M main`

## 2) Connected the project to GitHub repository
- Added GitHub remote (initially HTTPS): `https://github.com/Pomog/NoteApp2.git`
- HTTPS push failed because GitHub requires token/SSH (password auth not supported)

## 3) Switched to SSH authentication
- Generate keypair:
```bash
ssh-keygen -t ed25519 -C "your_email@example.com"

cat ~/.ssh/id_ed25519.pub
```

- Generated SSH keypair (Ed25519):
    - Private key: `~/.ssh/id_ed25519`
    - Public key: `~/.ssh/id_ed25519.pub`
- Printed public key: `cat ~/.ssh/id_ed25519.pub`
- Added the public key to GitHub:
    - GitHub → Settings → SSH and GPG keys → New SSH key
- Started ssh-agent and loaded the key:
    - `eval "$(ssh-agent -s)"`
    - `ssh-add ~/.ssh/id_ed25519`
- Verified loaded keys (check): `ssh-add -l`
- Tested GitHub SSH login: `ssh -T git@github.com`
- Changed remote from HTTPS to SSH:
    - `git remote set-url origin git@github.com:Pomog/NoteApp2.git`

## 4) Pushed code to GitHub
- Pushed `main` to GitHub and set upstream:
    - `git push -u origin main`

## Notes
- GUI prompt “Enter password to unlock the private key” = SSH key passphrase unlock (normal).
- Optional convenience: allow auto-unlock via keyring (“Automatically unlock this key whenever I’m logged in”).

## Emulator fix (Linux)

### Symptoms
- Emulator was visible in `adb devices` but showed as `offline` (e.g. `emulator-5554 offline`).
- Running/deploying the app to the emulator failed or hung (Gradle/DDMLib timeouts like `ShellCommandUnresponsiveException` / `TimeoutException`).
- Android Studio could show “Waiting for all target devices to come online”.

### What fixed it
1) **Killed the stuck/offline emulator**
```bash
adb devices
adb -s emulator-5554 emu kill
adb devices
```
Result: only the healthy emulator remained (e.g. emulator-5556 device).

2) **Adjusted AVD settings (more stable on Linux + NVIDIA)**
In Android Studio → Device Manager → Edit (pencil) AVD:
* Graphics acceleration: Software (SwiftShader)
* Default boot: Cold (avoid corrupted Quick Boot snapshots)
* RAM: 4 GB (optional)